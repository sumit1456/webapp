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

	public ResponseEntity<ExamApplicationDTO> fillForm(ExamApplication application) {

		if (application.getExam() == null || application.getStudent() == null) {
			throw new IllegalArgumentException("Exam and Student data must be provided");
		}

		Exam exam = er.findByExamNo(application.getExam().getExamNo())
				.orElseThrow(() -> new EntityNotFoundException("Invalid Exam Data"));

		Student stu = sr.findByStudentId(application.getStudent().getStudentId())
				.orElseThrow(() -> new EntityNotFoundException("Invalid Student Data"));

		ExamApplication savedApp;

		Optional<ExamApplication> existingApp = exam_app_repo.findByStudentAndExam(stu, exam);

		if (existingApp.isPresent()) {
			ExamApplication appToUpdate = existingApp.get();

			if (application.getFormData() != null) {
				appToUpdate.setFormData(application.getFormData());
			}

			if (application.getStatus() != null) {
				appToUpdate.setStatus(application.getStatus());
			} else if (appToUpdate.getStatus() == null) {
				appToUpdate.setStatus("SUBMITTED");
			}

			savedApp = exam_app_repo.save(appToUpdate);
		} else {
			application.setStudent(stu);
			application.setExam(exam);

			if (application.getStatus() == null) {
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

	public ExamApplicationDTO getApplicationDTO(long applicationId, long examNo) {
		ExamApplication ea = getFormByApplicationIdAndExamNo(applicationId, examNo);
		return mapToDTO(ea);
	}

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
		return dto;
	}

	// a service for dynamic filterning support for getting applications
	// works with dynamic filters which is present in ExamApplicationFilterDTO
	// eg region no , school id , exam centre etc

	public Page<ExamApplicationDTO> searchApplications(
			ExamApplicationFilterDTO filter,
			Pageable pageable) {

		// Build dynamic specification from filters
		Specification<ExamApplication> spec = ExamApplicationSpecification.build(filter);

		// Fetch paginated + filtered data from DB
		Page<ExamApplication> page = exam_app_repo.findAll(spec, pageable);

		// Convert Entity Page → DTO Page
		return page.map(this::mapToDTO);
	}

}
