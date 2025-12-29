package com.rasthrabhasha.result;

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

}
