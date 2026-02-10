package com.rasthrabhasha.examcentre;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rasthrabhasha.region.Region;
import com.rasthrabhasha.region.RegionRepository;

import com.rasthrabhasha.dto.ExamCentreDTO;
import java.util.stream.Collectors;

@Service
public class ExamCentreService {

	@Autowired
	private ExamCentreRepository examCentreRepository;

	@Autowired
	private RegionRepository regionRepository;

	public ExamCentre addExamCentre(Long regionId, ExamCentre examCentre) {

		System.out.println("=========================================================");

		Region region = regionRepository.findById(regionId)
				.orElseThrow(() -> new RuntimeException("Region not found"));

		examCentre.setRegion(region);

		System.out.println("THis is a exan Centre entity");

		System.out.println(examCentre);

		System.out.println("===========================================================");

		return examCentreRepository.save(examCentre);
	}

	public List<ExamCentre> getAllExams() {
		return examCentreRepository.findAll();

	}

	public List<ExamCentreDTO> getAllExamCentresDTOs() {
		return examCentreRepository.findAll().stream()
				.map(ec -> new ExamCentreDTO(
						ec.getCentreId(),
						ec.getCentreCode(),
						ec.getCentreName(),
						ec.getRegion() != null ? ec.getRegion().getRegionId() : null,
						ec.getRegion() != null ? ec.getRegion().getRegionName() : null))
				.collect(Collectors.toList());
	}

}
