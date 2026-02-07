package com.rasthrabhasha.application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rasthrabhasha.exam.Exam;
import com.rasthrabhasha.student.Student;
import java.util.Optional;

@Repository
public interface ExamApplicationRepository extends JpaRepository<ExamApplication, Long> {

	ExamApplication findByApplicationId(long applicationId);

	ExamApplication findByApplicationIdAndExam_ExamNo(long applicationId, long examNo);

	Optional<ExamApplication> findByStudentAndExam(Student student, Exam exam);
	
	 void deleteByExam_ExamNo(long examNo);

}
