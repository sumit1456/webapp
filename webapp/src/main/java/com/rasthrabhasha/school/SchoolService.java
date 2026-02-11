package com.rasthrabhasha.school;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rasthrabhasha.examcentre.ExamCentre;
import com.rasthrabhasha.examcentre.ExamCentreRepository;

import com.rasthrabhasha.dto.SchoolDTO;
import java.util.stream.Collectors;

@Service
public class SchoolService {

	@Autowired
	private SchoolRepository schoolRepository;

	@Autowired
	private ExamCentreRepository examCentreRepository;

	public School addSchool(Long centreId, School school) {

		ExamCentre examCentre = examCentreRepository.findById(centreId)
				.orElseThrow(() -> new RuntimeException("Exam Centre not found"));

		school.setExamCentre(examCentre);

		return schoolRepository.save(school);
	}

	public List<School> getAllSchools() {
		// TODO Auto-generated method stub
		List<School> list = schoolRepository.findAll();
		return list;
	}

	public List<SchoolDTO> getAllSchoolsDTOs() {
		return schoolRepository.findAll().stream()
				.map(s -> new SchoolDTO(
						s.getSchoolId(),
						s.getSchoolName(),
						s.getExamCentre() != null ? s.getExamCentre().getCentreId() : null,
						s.getExamCentre() != null ? s.getExamCentre().getCentreName() : null))
				.collect(Collectors.toList());
	}
}
