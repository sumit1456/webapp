package com.rasthrabhasha.application;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.rasthrabhasha.application.dto.ExamApplicationDTO;
import com.rasthrabhasha.application.dto.ExamApplicationFilterDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import com.rasthrabhasha.application.specification.ExamApplicationSpecification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import jakarta.transaction.Transactional;
import com.rasthrabhasha.common.dto.PageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rasthrabhasha.exam.Exam;
import com.rasthrabhasha.exam.ExamRepository;
import com.rasthrabhasha.exception.EntityNotFoundException;
import com.rasthrabhasha.student.Student;
import com.rasthrabhasha.student.StudentRepository;

@Service
public class ExamApplicationService {

	@Autowired
	StudentRepository sr;

	@Autowired
	ExamRepository er;

	@Autowired
	ExamApplicationRepository exam_app_repo;

	@CacheEvict(value = "applications", allEntries = true)
	public ResponseEntity<ExamApplicationDTO> fillForm(ExamApplicationDTO dto) {

		if (dto.getExamNo() == 0 || dto.getStudentId() == 0) {
			throw new IllegalArgumentException("Exam and Student data must be provided");
		}

		Exam exam = er.findByExamNo(dto.getExamNo())
				.orElseThrow(() -> new EntityNotFoundException("Invalid Exam Data"));

		Student stu = sr.findByStudentId(dto.getStudentId())
				.orElseThrow(() -> new EntityNotFoundException("Invalid Student Data"));

		ExamApplication savedApp;

		Optional<ExamApplication> existingApp = exam_app_repo.findByStudentAndExam(stu, exam);

		if (existingApp.isPresent()) {
			ExamApplication appToUpdate = existingApp.get();

			if (dto.getFormData() != null) {
				appToUpdate.setFormData(dto.getFormData());
			}

			if (dto.getStatus() != null) {
				appToUpdate.setStatus(dto.getStatus());
			} else if (appToUpdate.getStatus() == null) {
				appToUpdate.setStatus("SUBMITTED");
			}

			savedApp = exam_app_repo.save(appToUpdate);
		} else {
			ExamApplication application = new ExamApplication();
			application.setStudent(stu);
			application.setExam(exam);
			application.setFormData(dto.getFormData());

			if (dto.getStatus() != null) {
				application.setStatus(dto.getStatus());
			} else {
				application.setStatus("SUBMITTED");
			}

			savedApp = exam_app_repo.save(application);
		}

		return ResponseEntity.ok(mapToDTO(savedApp));
	}

	public ExamApplication getFormByApplicationIdAndExamNo(long applicationId, long examNo) {
		ExamApplication app = exam_app_repo.findByApplicationIdAndExam_ExamNo(applicationId, examNo);
		if (app == null)
			throw new EntityNotFoundException("Invalid Application Id or Exam No");

		return app;

	}

	@Cacheable(value = "applications", key = "#applicationId + ':' + #examNo")
	public ExamApplicationDTO getApplicationDTO(long applicationId, long examNo) {
		ExamApplication ea = getFormByApplicationIdAndExamNo(applicationId, examNo);
		return mapToDTO(ea);
	}

	@Cacheable(value = "applications", key = "'allDTOs'")
	public List<ExamApplicationDTO> getAllApplicationsDTOs() {
		return exam_app_repo.findAll().stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
	}

	private ExamApplicationDTO mapToDTO(ExamApplication ea) {
		ExamApplicationDTO dto = new ExamApplicationDTO();
		dto.setApplicationId(ea.getApplicationId());
		dto.setExamNo(ea.getExam() != null ? ea.getExam().getExamNo() : 0);
		dto.setExamName(ea.getExam() != null ? ea.getExam().getExam_name() : null);
		dto.setStudentId(ea.getStudent() != null ? ea.getStudent().getStudentId() : 0);
		dto.setStudentName(
				ea.getStudent() != null ? ea.getStudent().getFirstName() + " " + ea.getStudent().getLastName() : null);
		dto.setStatus(ea.getStatus());
		dto.setFormData(ea.getFormData());
		dto.setRollNo(ea.getRollNo());
		dto.setCentreId(ea.getCentreId());
		dto.setIsHallTicketGenerated(ea.getIsHallTicketGenerated());
		dto.setHasResult(ea.getExamResult() != null);
		return dto;
	}

	@Transactional
	@CacheEvict(value = "applications", allEntries = true)
	public ExamApplicationDTO generateHallTicket(Long applicationId) {
		ExamApplication app = exam_app_repo.findById(applicationId)
				.orElseThrow(() -> new EntityNotFoundException("Application not found"));

		processHallTicketGeneration(app);

		return mapToDTO(exam_app_repo.save(app));
	}

	private void processHallTicketGeneration(ExamApplication app) {
		if (!"APPROVED".equals(app.getStatus())) {
			throw new IllegalStateException("Hall ticket can only be generated for APPROVED applications");
		}

		Student student = app.getStudent();
		if (student == null || student.getSchool() == null || student.getSchool().getExamCentre() == null) {
			throw new IllegalStateException("Student, School or Exam Centre information is missing for this application");
		}

		com.rasthrabhasha.school.School school = student.getSchool();
		com.rasthrabhasha.examcentre.ExamCentre centre = school.getExamCentre();
		com.rasthrabhasha.region.Region region = centre.getRegion();

		if (region == null) {
			throw new IllegalStateException("Region information is missing for the Exam Centre");
		}

		Long regionId = region.getRegionId();
		Long centreId = centre.getCentreId();
		Long schoolId = school.getSchoolId();

		// Generate roll no: {regionId}{centreId}{schoolId}{applicationId}
		String rollNo = String.format("%d%d%d%d", regionId, centreId, schoolId, app.getApplicationId());

		app.setRollNo(rollNo);
		app.setCentreId(centreId);
		app.setIsHallTicketGenerated(true);
	}

	@Transactional
	@CacheEvict(value = "applications", allEntries = true)
	public void batchGenerateHallTickets() {
		List<ExamApplication> apps = exam_app_repo.findByStatusAndIsHallTicketGenerated("APPROVED", false);
		for (ExamApplication app : apps) {
			try {
				processHallTicketGeneration(app);
				exam_app_repo.save(app);
			} catch (Exception e) {
				// Log error for specific application but continue batch
				System.err.println("Failed to generate hall ticket for application ID: " + app.getApplicationId() + ". Error: " + e.getMessage());
			}
		}
	}

	// a service for dynamic filterning support for getting applications
	// works with dynamic filters which is present in ExamApplicationFilterDTO
	// eg region no , school id , exam centre etc

	@Cacheable(value = "applications", key = "'search:' + #filter.hashCode() + ':' + #pageable.pageNumber + ':' + #pageable.pageSize")
	public PageResponse<ExamApplicationDTO> searchApplications(
			ExamApplicationFilterDTO filter,
			Pageable pageable) {

		// Build dynamic specification from filters
		Specification<ExamApplication> spec = ExamApplicationSpecification.build(filter);

		// Fetch paginated + filtered data from DB
		Page<ExamApplicationDTO> page = exam_app_repo.findAll(spec, pageable)
				.map(this::mapToDTO);

		// Convert Entity Page → DTO Page
		return new PageResponse<>(page);
	}

	@CacheEvict(value = "applications", allEntries = true)
	@Transactional
	public ExamApplicationDTO updateApplication(long id, ExamApplicationDTO dto) {
		ExamApplication existing = exam_app_repo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Application not found"));

		if (dto.getFormData() != null) {
			existing.setFormData(dto.getFormData());
		}
		if (dto.getStatus() != null) {
			existing.setStatus(dto.getStatus());
		}

		return mapToDTO(exam_app_repo.save(existing));
	}

	@Autowired
	com.rasthrabhasha.result.ExamResultRepository resultRepo;

	@CacheEvict(value = "applications", allEntries = true)
	@Transactional
	public void deleteApplication(long id) {
		resultRepo.deleteByApplication_ApplicationId(id);
		exam_app_repo.deleteById(id);
	}

}
