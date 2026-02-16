package com.rasthrabhasha.examcentre;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rasthrabhasha.region.Region;
import com.rasthrabhasha.region.RegionRepository;

import com.rasthrabhasha.examcentre.dto.ExamCentreDTO;
import com.rasthrabhasha.examcentre.dto.ExamCentreFilterDTO;
import com.rasthrabhasha.examcentre.specification.ExamCentreSpecification;

import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@Service
public class ExamCentreService {

	@Autowired
	private ExamCentreRepository examCentreRepository;

	@Autowired
	private RegionRepository regionRepository;

	public ExamCentreDTO addExamCentre(Long regionId, ExamCentre examCentre) {

		Region region = regionRepository.findById(regionId)
				.orElseThrow(() -> new RuntimeException("Region not found"));

		examCentre.setRegion(region);

		ExamCentre savedCentre = examCentreRepository.save(examCentre);
		return mapToDTO(savedCentre);
	}

	private ExamCentreDTO mapToDTO(ExamCentre ec) {
		return new ExamCentreDTO(
				ec.getCentreId(),
				ec.getCentreCode(),
				ec.getCentreName(),
				ec.getRegion() != null ? ec.getRegion().getRegionId() : null,
				ec.getRegion() != null ? ec.getRegion().getRegionName() : null);
	}

	public List<ExamCentre> getAllExams() {
		return examCentreRepository.findAll();

	}

	public List<ExamCentreDTO> getAllExamCentresDTOs() {
		return examCentreRepository.findAll().stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
	}

	public Page<ExamCentreDTO> searchExamCentres(ExamCentreFilterDTO filter, Pageable pageable) {
		Specification<ExamCentre> spec = ExamCentreSpecification.build(filter);

		return examCentreRepository.findAll(spec, pageable)
				.map(this::mapToDTO);
	}

}
