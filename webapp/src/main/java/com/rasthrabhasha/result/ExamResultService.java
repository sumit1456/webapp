package com.rasthrabhasha.result;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import com.rasthrabhasha.result.dto.ExamResultDTO;
import com.rasthrabhasha.result.dto.ExamResultFilterDTO;
import com.rasthrabhasha.result.specification.ExamResultSpecification;
import com.rasthrabhasha.student.Student;
import com.rasthrabhasha.exam.Exam;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.rasthrabhasha.common.dto.PageResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rasthrabhasha.application.ExamApplication;
import com.rasthrabhasha.application.ExamApplicationRepository;
import com.rasthrabhasha.exception.EntityNotFoundException;

@Service
public class ExamResultService {

	@Autowired
	ExamApplicationRepository ear;

	@Autowired
	ExamResultRepository err;

	@CacheEvict(value = "results", allEntries = true)
	public ExamResultDTO createResult(ExamResult er) {
		if (er.getApplication() == null || er.getApplication().getApplicationId() == null) {
			throw new IllegalArgumentException("Exam Application information is required");
		}

		long applicationId = er.getApplication().getApplicationId();
		ExamApplication examApplication = ear.findByApplicationId(applicationId);
		if (examApplication == null)
			throw new EntityNotFoundException("Invalid Application Id");
		
		er.setApplication(examApplication);
		ExamResult res = err.save(er);
		
		examApplication.setExamResult(res);
		ear.save(examApplication);
		
		return mapToDTO(res);
	}

	public ExamResult getExamResult(long applicationId) {
		ExamResult res = err.findByApplicationId(applicationId);
		if (res == null)
			throw new EntityNotFoundException("Invalid Application Id");
		return res;
	}

	public ExamResultDTO getResultDTO(long applicationId) {
		ExamResult er = getExamResult(applicationId);
		return mapToDTO(er);
	}

	@Cacheable(value = "results", key = "'allDTOs'")
	public List<ExamResultDTO> getAllResultsDTOs() {
		return err.findAll().stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
	}

	@Cacheable(value = "results", key = "'student:' + #studentId")
	public List<ExamResultDTO> getStudentResultsDTOs(long studentId) {
		return err.findByStudentId(studentId).stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
	}

	@Cacheable(value = "results", key = "'search:' + #filter.hashCode() + ':' + #pageable.pageNumber + ':' + #pageable.pageSize")
	public PageResponse<ExamResultDTO> searchExamResults(ExamResultFilterDTO filter, Pageable pageable) {
		System.out.println("Request sent to database");
		Specification<ExamResult> spec = ExamResultSpecification.build(filter);
		Page<ExamResultDTO> page = err.findAll(spec, pageable).map(this::mapToDTO);
		return new PageResponse<>(page);
	}

	@CacheEvict(value = "results", allEntries = true)
	public ExamResultDTO updateResult(long id, ExamResult er) {
		ExamResult existing = err.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Exam Result not found with id: " + id));

		existing.setResultData(er.getResultData());
		existing.setTotalMarks(er.getTotalMarks());
		existing.setPercentage(er.getPercentage());
		existing.setPublishedAt(er.getPublishedAt());

		return mapToDTO(err.save(existing));
	}

	@CacheEvict(value = "results", allEntries = true)
	public void deleteResult(long id) {
		if (!err.existsById(id)) {
			throw new EntityNotFoundException("Exam Result not found with id: " + id);
		}
		err.deleteById(id);
	}

	private ExamResultDTO mapToDTO(ExamResult er) {

		ExamResultDTO dto = new ExamResultDTO();

		dto.setId(er.getId());

		ExamApplication app = er.getApplication();
		Exam exam = (app != null) ? app.getExam() : null;
		Student stu = (app!=null) ? app.getStudent() : null;

		dto.setApplicationId(app != null ? app.getApplicationId() : null);
		dto.setExamName(exam != null ? exam.getExam_name() : null);
		dto.setResultData(er.getResultData());
		dto.setPublishedAt(er.getPublishedAt());
		dto.setTotalMarks(er.getTotalMarks());
		dto.setPercentage(er.getPercentage());
		if (stu != null) {
			dto.setStudentName(stu.getFirstName() + " " + (stu.getMiddleName() != null ? stu.getMiddleName() + " " : "") + stu.getLastName());
		}
	

		return dto;
	}

}
