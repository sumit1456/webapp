package com.rasthrabhasha.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	Optional<Student> findByStudentId(long studentId);
	
	@Query("""
	        SELECT DISTINCT s
	        FROM Student s
	        LEFT JOIN FETCH s.applications
	        WHERE s.studentId = :studentId
	    """)
	    Optional<Student> findStudentWithApplications(@Param("studentId") long studentId);
	
	

}
