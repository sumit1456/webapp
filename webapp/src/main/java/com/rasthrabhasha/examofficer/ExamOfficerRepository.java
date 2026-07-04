package com.rasthrabhasha.examofficer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamOfficerRepository extends JpaRepository<ExamOfficer, Long> {

	Optional<ExamOfficer> findByUsername(String username);
}
