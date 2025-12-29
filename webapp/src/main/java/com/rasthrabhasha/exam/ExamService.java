package com.rasthrabhasha.exam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService {

	@Autowired
	ExamRepository exam_repo;

	public List<Exam> getAllExams() {
		return exam_repo.findAll();
	}

}
