package com.rasthrabhasha.region;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rasthrabhasha.region.dto.RegionDTO;
import com.rasthrabhasha.region.dto.RegionFilterDTO;
import com.rasthrabhasha.region.specification.RegionSpecification;

import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@Service
public class RegionService {

	@Autowired
	private RegionRepository regionRepository;

	public RegionDTO addRegion(Region region) {
		Region savedRegion = regionRepository.save(region);
		return mapToDTO(savedRegion);
	}

	private RegionDTO mapToDTO(Region r) {
		return new RegionDTO(r.getRegionId(), r.getRegionName());
	}

	public List<Region> getAllRegions() {
		return regionRepository.findAll();
	}

	public List<RegionDTO> getAllRegionsDTOs() {
		return regionRepository.findAll().stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
	}

	public Page<RegionDTO> searchRegions(RegionFilterDTO filter, Pageable pageable) {
		Specification<Region> spec = RegionSpecification.build(filter);
		return regionRepository.findAll(spec, pageable)
				.map(this::mapToDTO);
	}

}
