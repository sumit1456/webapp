package com.rasthrabhasha.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

	java.util.Optional<Exam> findByExamNo(long examNo);

}
