package com.rasthrabhasha.result;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, Long>{

	
	@Query("""
		       SELECT a
		       FROM ExamResult a
		       WHERE a.application.applicationId = :applicationId
		       """)
		ExamResult findByApplicationId(@Param("applicationId") Long applicationId);
	
	
	

}
