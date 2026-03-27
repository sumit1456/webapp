package com.rasthrabhasha.region;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rasthrabhasha.common.dto.PageResponse;
import com.rasthrabhasha.region.dto.RegionDTO;
import com.rasthrabhasha.region.dto.RegionFilterDTO;
import com.rasthrabhasha.region.specification.RegionSpecification;

import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import jakarta.transaction.Transactional;

@Service
public class RegionService {

	@Autowired
	private RegionRepository regionRepository;
    
	
	@CacheEvict(value = { "regions", "analytics_summary", "analytics_counts" }, allEntries = true)
	public RegionDTO addRegion(Region region) {
		Region savedRegion = regionRepository.save(region);
		return mapToDTO(savedRegion);
	}

	private RegionDTO mapToDTO(Region r) {
		return new RegionDTO(r.getRegionId(), r.getRegionName());
	}
    
    
	
	public List<RegionDTO> getAllRegionsDTOs() {
		
		return regionRepository.findAll().stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
	}

	@Cacheable(value = "regions", key = "'search:' + #filter.hashCode() + ':' + #pageable.pageNumber + ':' + #pageable.pageSize")
	public PageResponse<RegionDTO> searchRegions(RegionFilterDTO filter, Pageable pageable) {

	    System.out.println("it went to database");

	    Specification<Region> spec = RegionSpecification.build(filter);

	    Page<RegionDTO> page = regionRepository.findAll(spec, pageable)
	            .map(this::mapToDTO);

	    return new PageResponse<>(page);
	}

	@CacheEvict(value = { "regions", "analytics_summary", "analytics_counts" }, allEntries = true)
	@Transactional
	public RegionDTO updateRegion(long id, Region updatedRegion) {
		Region region = regionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Region not found with id: " + id));
		region.setRegionName(updatedRegion.getRegionName());
		Region savedRegion = regionRepository.save(region);
		return mapToDTO(savedRegion);
	}

	@CacheEvict(value = { "regions", "analytics_summary", "analytics_counts" }, allEntries = true)
	@Transactional
	public void deleteRegion(long id) {
		regionRepository.deleteById(id);
	}

}
