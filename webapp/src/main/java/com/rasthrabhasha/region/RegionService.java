package com.rasthrabhasha.region;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
