package com.rasthrabhasha.application;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	public ExamApplication fillForm(ExamApplication application) {
		if (application.getExam() == null || application.getStudent() == null) {
			throw new IllegalArgumentException("Exam and Student data must be provided");
		}

		Exam exam = er.findByExamNo(application.getExam().getExamNo())
				.orElseThrow(() -> new EntityNotFoundException("Invalid Exam Data"));

		Student stu = sr.findByStudentId(application.getStudent().getStudentId())
				.orElseThrow(() -> new EntityNotFoundException("Invalid Student Data"));

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
			return exam_app_repo.save(appToUpdate);
		}

		application.setStudent(stu);
		application.setExam(exam);
		if (application.getStatus() == null) {
			application.setStatus("SUBMITTED");
		}

		return exam_app_repo.save(application);

	}

	public ExamApplication getFormByApplicationIdAndExamNo(long applicationId, long examNo) {
		ExamApplication app = exam_app_repo.findByApplicationIdAndExam_ExamNo(applicationId, examNo);
		if (app == null)
			throw new EntityNotFoundException("Invalid Application Id or Exam No");

		return app;

	}

	
}
