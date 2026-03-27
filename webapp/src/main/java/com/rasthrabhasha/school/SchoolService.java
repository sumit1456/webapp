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

	@CacheEvict(value = { "schools", "analytics_summary", "analytics_counts" }, allEntries = true)
	public SchoolDTO addSchool(Long centreId, School school) {

		ExamCentre examCentre = examCentreRepository.findById(centreId)
				.orElseThrow(() -> new RuntimeException("Exam Centre not found"));

		school.setExamCentre(examCentre);

		School savedSchool = schoolRepository.save(school);
		return mapToDTO(savedSchool);
	}

	private SchoolDTO mapToDTO(School s) {
		SchoolDTO dto = new SchoolDTO(
				s.getSchoolId(),
				s.getSchoolName(),
				s.getExamCentre() != null ? s.getExamCentre().getCentreId() : null,
				s.getExamCentre() != null ? s.getExamCentre().getCentreName() : null);
		dto.setSchoolCode(s.getSchoolCode());
		dto.setBoardAffiliation(s.getBoardAffiliation());
		dto.setMediumOfInstruction(s.getMediumOfInstruction());
		dto.setEstablishmentYear(s.getEstablishmentYear());
		dto.setPrincipalName(s.getPrincipalName());
		dto.setPrincipalContactNumber(s.getPrincipalContactNumber());
		dto.setSecondaryContactNumber(s.getSecondaryContactNumber());
		dto.setOfficialEmail(s.getOfficialEmail());
		dto.setWebsiteUrl(s.getWebsiteUrl());
		dto.setSeatingCapacity(s.getSeatingCapacity());
		dto.setNumberOfClassrooms(s.getNumberOfClassrooms());
		dto.setCctvAvailable(s.getCctvAvailable());
		dto.setAddress(s.getAddress());
		dto.setPrincipalSignatureUrl(s.getPrincipalSignatureUrl());
		dto.setSchoolStampUrl(s.getSchoolStampUrl());
		return dto;
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
	
	public SchoolDTO getSchoolById(long id) {
		
		School school = schoolRepository.findById(id).orElseThrow(() -> new RuntimeException("School id does not exist"));
		return mapToDTO(school);
	}

	@CacheEvict(value = { "schools", "analytics_summary", "analytics_counts" }, allEntries = true)
	public SchoolDTO updateSchool(SchoolDTO dto) {
		School school = schoolRepository.findById(dto.getSchoolId())
				.orElseThrow(() -> new RuntimeException("School id does not exist"));

		school.setSchoolName(dto.getSchoolName());
		school.setSchoolCode(dto.getSchoolCode());
		school.setBoardAffiliation(dto.getBoardAffiliation());
		school.setMediumOfInstruction(dto.getMediumOfInstruction());
		school.setEstablishmentYear(dto.getEstablishmentYear());
		school.setPrincipalName(dto.getPrincipalName());
		school.setPrincipalContactNumber(dto.getPrincipalContactNumber());
		school.setSecondaryContactNumber(dto.getSecondaryContactNumber());
		school.setOfficialEmail(dto.getOfficialEmail());
		school.setWebsiteUrl(dto.getWebsiteUrl());
		school.setSeatingCapacity(dto.getSeatingCapacity());
		school.setNumberOfClassrooms(dto.getNumberOfClassrooms());
		school.setCctvAvailable(dto.getCctvAvailable());
		school.setAddress(dto.getAddress());
		school.setPrincipalSignatureUrl(dto.getPrincipalSignatureUrl());
		school.setSchoolStampUrl(dto.getSchoolStampUrl());

		if (dto.getCentreId() != null && (school.getExamCentre() == null
				|| !school.getExamCentre().getCentreId().equals(dto.getCentreId()))) {
			ExamCentre examCentre = examCentreRepository.findById(dto.getCentreId())
					.orElseThrow(() -> new RuntimeException("Exam Centre not found"));
			school.setExamCentre(examCentre);
		}

		School updatedSchool = schoolRepository.save(school);
		return mapToDTO(updatedSchool);
	}
	
	@CacheEvict(value = { "schools", "analytics_summary", "analytics_counts" }, allEntries = true)
	public void deleteSchool(long id) {
		schoolRepository.deleteById(id);
	}
}
