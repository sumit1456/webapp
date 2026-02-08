package com.rasthrabhasha.school;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rasthrabhasha.examcentre.ExamCentre;
import com.rasthrabhasha.examcentre.ExamCentreRepository;

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
}
