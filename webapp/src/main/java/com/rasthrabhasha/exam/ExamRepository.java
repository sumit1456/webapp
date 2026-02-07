package com.rasthrabhasha.exam;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

	Optional<Exam> findByExamNo(long examNo);
	
//	 void deleteByApplication_Exam_ExamNo(Long examNo);
	
	

}
