package com.rasthrabhasha.analytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rasthrabhasha.analytics.dto.AnalyticsResponseDTO;

@RestController
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    
    @GetMapping("/summary")
    public ResponseEntity<AnalyticsResponseDTO> getSummary() {
        return ResponseEntity.ok(analyticsService.getAnalyticsSummary());
    }

    
    @GetMapping("/counts/school/{schoolId}/students")
    public ResponseEntity<Long> getStudentCountBySchool(@PathVariable long schoolId) {
        return ResponseEntity.ok(analyticsService.getStudentCountBySchool(schoolId));
    }

  
    @GetMapping("/counts/region/{regionId}/exam-centres")
    public ResponseEntity<Long> getExamCentreCountByRegion(@PathVariable long regionId) {
        return ResponseEntity.ok(analyticsService.getExamCentreCountByRegion(regionId));
    }

    
    @GetMapping("/counts/exam-centre/{centreId}/schools")
    public ResponseEntity<Long> getSchoolCountByExamCentre(@PathVariable long centreId) {
        return ResponseEntity.ok(analyticsService.getSchoolCountByExamCentre(centreId));
    }

   
    @GetMapping("/counts/region/{regionId}/students")
    public ResponseEntity<Long> getStudentCountByRegion(@PathVariable long regionId) {
        return ResponseEntity.ok(analyticsService.getStudentCountByRegion(regionId));
    }

   
    @GetMapping("/counts/region/{regionId}/schools")
    public ResponseEntity<Long> getSchoolCountByRegion(@PathVariable long regionId) {
        return ResponseEntity.ok(analyticsService.getSchoolCountByRegion(regionId));
    }

   
    @GetMapping("/counts/exam-centre/{centreId}/students")
    public ResponseEntity<Long> getStudentCountByExamCentre(@PathVariable long centreId) {
        return ResponseEntity.ok(analyticsService.getStudentCountByExamCentre(centreId));
    }
}
