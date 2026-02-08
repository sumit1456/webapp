package com.rasthrabhasha.examcentre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExamCentreRepository extends JpaRepository<ExamCentre, Long> {
	
	

}
