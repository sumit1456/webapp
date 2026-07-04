package com.rasthrabhasha.analytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rasthrabhasha.analytics.dto.AnalyticsResponseDTO;
import com.rasthrabhasha.common.enums.Permission;
import com.rasthrabhasha.common.util.PermissionUtils;

@RestController
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/summary")
    public ResponseEntity<?> getSummary() {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_DASHBOARD);
        if (err != null) return err;
        return ResponseEntity.ok(analyticsService.getAnalyticsSummary());
    }

    @GetMapping("/counts/school/{schoolId}/students")
    public ResponseEntity<?> getStudentCountBySchool(@PathVariable long schoolId) {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_DASHBOARD);
        if (err != null) return err;
        return ResponseEntity.ok(analyticsService.getStudentCountBySchool(schoolId));
    }

    @GetMapping("/counts/region/{regionId}/exam-centres")
    public ResponseEntity<?> getExamCentreCountByRegion(@PathVariable long regionId) {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_DASHBOARD);
        if (err != null) return err;
        return ResponseEntity.ok(analyticsService.getExamCentreCountByRegion(regionId));
    }

    @GetMapping("/counts/exam-centre/{centreId}/schools")
    public ResponseEntity<?> getSchoolCountByExamCentre(@PathVariable long centreId) {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_DASHBOARD);
        if (err != null) return err;
        return ResponseEntity.ok(analyticsService.getSchoolCountByExamCentre(centreId));
    }

    @GetMapping("/counts/region/{regionId}/students")
    public ResponseEntity<?> getStudentCountByRegion(@PathVariable long regionId) {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_DASHBOARD);
        if (err != null) return err;
        return ResponseEntity.ok(analyticsService.getStudentCountByRegion(regionId));
    }

    @GetMapping("/counts/region/{regionId}/schools")
    public ResponseEntity<?> getSchoolCountByRegion(@PathVariable long regionId) {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_DASHBOARD);
        if (err != null) return err;
        return ResponseEntity.ok(analyticsService.getSchoolCountByRegion(regionId));
    }

    @GetMapping("/counts/exam-centre/{centreId}/students")
    public ResponseEntity<?> getStudentCountByExamCentre(@PathVariable long centreId) {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_DASHBOARD);
        if (err != null) return err;
        return ResponseEntity.ok(analyticsService.getStudentCountByExamCentre(centreId));
    }
}
