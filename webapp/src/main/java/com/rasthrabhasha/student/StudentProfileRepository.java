package com.rasthrabhasha.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
	
	
	 
	  Optional<StudentProfile> findByStudent_StudentId(long id);
}


