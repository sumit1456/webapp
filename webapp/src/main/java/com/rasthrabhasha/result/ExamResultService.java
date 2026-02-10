package com.rasthrabhasha.result;

import java.util.List;
import java.util.stream.Collectors;
import com.rasthrabhasha.dto.ExamResultDTO;

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

	public ExamResult createResult(ExamResult er) {

		ExamApplication axamApplication = ear.findByApplicationId(er.getApplication().getApplicationId());
		if (axamApplication == null)
			throw new EntityNotFoundException("Invalid Application Id");

		er.setApplication(axamApplication);
		return err.save(er);

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

	private ExamResultDTO mapToDTO(ExamResult er) {
		ExamResultDTO dto = new ExamResultDTO();
		dto.setId(er.getId());
		dto.setApplicationId(er.getApplication() != null ? er.getApplication().getApplicationId() : null);
		dto.setStudentName(er.getApplication() != null && er.getApplication().getStudent() != null
				? er.getApplication().getStudent().getFirstName() + " " + er.getApplication().getStudent().getLastName()
				: null);
		dto.setExamName(er.getApplication() != null && er.getApplication().getExam() != null
				? er.getApplication().getExam().getExam_name()
				: null);
		dto.setResultData(er.getResultData());
		dto.setPublishedAt(er.getPublishedAt());
		dto.setTotalMarks(er.getTotalMarks());
		dto.setPercentage(er.getPercentage());
		return dto;
	}

}
