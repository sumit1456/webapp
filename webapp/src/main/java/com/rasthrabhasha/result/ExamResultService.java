package com.rasthrabhasha.result;

import java.util.List;
import java.util.stream.Collectors;
import com.rasthrabhasha.result.dto.ExamResultDTO;
import com.rasthrabhasha.result.dto.ExamResultFilterDTO;
import com.rasthrabhasha.result.specification.ExamResultSpecification;
import com.rasthrabhasha.exam.Exam;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

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

	public ExamResultDTO createResult(ExamResult er) {

		ExamApplication axamApplication = ear.findByApplicationId(er.getApplication().getApplicationId());
		if (axamApplication == null)
			throw new EntityNotFoundException("Invalid Application Id");

		er.setApplication(axamApplication);
		ExamResult res = err.save(er);

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

	public List<ExamResultDTO> getAllResultsDTOs() {
		return err.findAll().stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
	}

	public List<ExamResultDTO> getStudentResultsDTOs(long studentId) {
		return err.findByStudentId(studentId).stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
	}

	public Page<ExamResultDTO> searchExamResults(ExamResultFilterDTO filter, Pageable pageable) {
		Specification<ExamResult> spec = ExamResultSpecification.build(filter);
		return err.findAll(spec, pageable).map(this::mapToDTO);
	}

	private ExamResultDTO mapToDTO(ExamResult er) {

		ExamResultDTO dto = new ExamResultDTO();

		dto.setId(er.getId());

		ExamApplication app = er.getApplication();
		Exam exam = (app != null) ? app.getExam() : null;

		dto.setApplicationId(app != null ? app.getApplicationId() : null);
		dto.setExamName(exam != null ? exam.getExam_name() : null);

		dto.setResultData(er.getResultData());
		dto.setPublishedAt(er.getPublishedAt());
		dto.setTotalMarks(er.getTotalMarks());
		dto.setPercentage(er.getPercentage());

		return dto;
	}

}
