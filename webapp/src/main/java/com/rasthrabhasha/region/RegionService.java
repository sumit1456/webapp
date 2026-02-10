package com.rasthrabhasha.region;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rasthrabhasha.dto.RegionDTO;
import java.util.stream.Collectors;

@Service
public class RegionService {

	@Autowired
	private RegionRepository regionRepository;

	public Region addRegion(Region region) {
		return regionRepository.save(region);
	}

	public List<Region> getAllRegions() {
		return regionRepository.findAll();
	}

	public List<RegionDTO> getAllRegionsDTOs() {
		return regionRepository.findAll().stream()
				.map(r -> new RegionDTO(r.getRegionId(), r.getRegionName()))
				.collect(Collectors.toList());
	}

}
