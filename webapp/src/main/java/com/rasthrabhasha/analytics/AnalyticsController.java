package com.rasthrabhasha.analytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rasthrabhasha.analytics.dto.AnalyticsResponseDTO;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    // GET /api/analytics/summary
    @GetMapping("/summary")
    public ResponseEntity<AnalyticsResponseDTO> getSummary() {
        return ResponseEntity.ok(analyticsService.getAnalyticsSummary());
    }

    // GET /api/analytics/counts/school/{schoolId}/students
    @GetMapping("/counts/school/{schoolId}/students")
    public ResponseEntity<Long> getStudentCountBySchool(@PathVariable long schoolId) {
        return ResponseEntity.ok(analyticsService.getStudentCountBySchool(schoolId));
    }

    // GET /api/analytics/counts/region/{regionId}/exam-centres
    @GetMapping("/counts/region/{regionId}/exam-centres")
    public ResponseEntity<Long> getExamCentreCountByRegion(@PathVariable long regionId) {
        return ResponseEntity.ok(analyticsService.getExamCentreCountByRegion(regionId));
    }

    // GET /api/analytics/counts/exam-centre/{centreId}/schools
    @GetMapping("/counts/exam-centre/{centreId}/schools")
    public ResponseEntity<Long> getSchoolCountByExamCentre(@PathVariable long centreId) {
        return ResponseEntity.ok(analyticsService.getSchoolCountByExamCentre(centreId));
    }

    // GET /api/analytics/counts/region/{regionId}/students
    @GetMapping("/counts/region/{regionId}/students")
    public ResponseEntity<Long> getStudentCountByRegion(@PathVariable long regionId) {
        return ResponseEntity.ok(analyticsService.getStudentCountByRegion(regionId));
    }

    // GET /api/analytics/counts/region/{regionId}/schools
    @GetMapping("/counts/region/{regionId}/schools")
    public ResponseEntity<Long> getSchoolCountByRegion(@PathVariable long regionId) {
        return ResponseEntity.ok(analyticsService.getSchoolCountByRegion(regionId));
    }

    // GET /api/analytics/counts/exam-centre/{centreId}/students
    @GetMapping("/counts/exam-centre/{centreId}/students")
    public ResponseEntity<Long> getStudentCountByExamCentre(@PathVariable long centreId) {
        return ResponseEntity.ok(analyticsService.getStudentCountByExamCentre(centreId));
    }
}
