package com.rasthrabhasha.school;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.rasthrabhasha.examcentre.ExamCentre;
import com.rasthrabhasha.examcentre.ExamCentreRepository;

import com.rasthrabhasha.school.dto.SchoolDTO;
import com.rasthrabhasha.school.dto.SchoolFilterDTO;
import com.rasthrabhasha.school.specification.SchoolSpecification;

import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.rasthrabhasha.common.dto.PageResponse;

@Service
public class SchoolService {

	@Autowired
	private SchoolRepository schoolRepository;

	@Autowired
	private ExamCentreRepository examCentreRepository;

	@CacheEvict(value = "schools", allEntries = true)
	public SchoolDTO addSchool(Long centreId, School school) {

		ExamCentre examCentre = examCentreRepository.findById(centreId)
				.orElseThrow(() -> new RuntimeException("Exam Centre not found"));

		school.setExamCentre(examCentre);

		School savedSchool = schoolRepository.save(school);
		return mapToDTO(savedSchool);
	}

	private SchoolDTO mapToDTO(School s) {
		return new SchoolDTO(
				s.getSchoolId(),
				s.getSchoolName(),
				s.getExamCentre() != null ? s.getExamCentre().getCentreId() : null,
				s.getExamCentre() != null ? s.getExamCentre().getCentreName() : null);
	}

	public List<School> getAllSchools() {
		// TODO Auto-generated method stub
		List<School> list = schoolRepository.findAll();
		return list;
	}

	@Cacheable(value = "schools", key = "'allDTOs'")
	public List<SchoolDTO> getAllSchoolsDTOs() {
		return schoolRepository.findAll().stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
	}

	@Cacheable(value = "schools", key = "'search:' + #filter.hashCode() + ':' + #pageable.pageNumber + ':' + #pageable.pageSize")
	public PageResponse<SchoolDTO> searchSchools(SchoolFilterDTO filter, Pageable pageable) {
		System.out.println("Request sent to database");
		Specification<School> spec = SchoolSpecification.build(filter);

		Page<SchoolDTO> page = schoolRepository.findAll(spec, pageable)
				.map(this::mapToDTO);
		return new PageResponse<>(page);
	}
}
