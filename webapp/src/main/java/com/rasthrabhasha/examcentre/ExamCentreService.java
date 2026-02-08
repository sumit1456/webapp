package com.rasthrabhasha.examcentre;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rasthrabhasha.region.Region;
import com.rasthrabhasha.region.RegionRepository;

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

}
