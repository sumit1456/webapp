package com.rasthrabhasha.application;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Pageable;
import com.rasthrabhasha.common.dto.PageResponse;
import com.rasthrabhasha.common.enums.Permission;
import com.rasthrabhasha.common.util.PermissionUtils;

import com.rasthrabhasha.application.dto.ExamApplicationDTO;
import com.rasthrabhasha.application.dto.ExamApplicationFilterDTO;

@RestController
public class ExamApplicationController {

    @Autowired
    ExamApplicationService eas;

    @PostMapping("/exam-applications")
    public ResponseEntity<?> createApplication(@RequestBody ExamApplicationDTO dto) {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.SUBMIT_APPLICATION);
        if (err != null) return err;
        System.out.println(dto.getExamNo());
        System.out.println(dto.getStudentId());
        return eas.fillForm(dto);
    }

    @GetMapping("/exam-applications-byId")
    public ResponseEntity<?> getForm(@RequestParam long applicationId, @RequestParam long examNo) {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_APPLICATIONS);
        if (err != null) return err;
        return ResponseEntity.ok(eas.getApplicationDTO(applicationId, examNo));
    }

    @GetMapping("/getAllApplications/{id}")
    public ResponseEntity<?> getAllApplications() {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_APPLICATIONS);
        if (err != null) return err;
        return ResponseEntity.ok(eas.getAllApplicationsDTOs());
    }

    @GetMapping("/exam-applications")
    public ResponseEntity<?> searchApplication(
            ExamApplicationFilterDTO filter,
            Pageable pageable) {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_APPLICATIONS);
        if (err != null) return err;
        return ResponseEntity.ok(eas.searchApplications(filter, pageable));
    }

    @PutMapping("/exam-applications/{id}")
    public ResponseEntity<?> updateApplication(
            @PathVariable long id,
            @RequestBody ExamApplicationDTO dto) {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.MANAGE_APPLICATIONS);
        if (err != null) return err;
        return ResponseEntity.ok(eas.updateApplication(id, dto));
    }

    @DeleteMapping("/exam-applications/{id}")
    public ResponseEntity<?> deleteApplication(@PathVariable long id) {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.MANAGE_APPLICATIONS);
        if (err != null) return err;
        eas.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/exam-applications/{id}/generate-hall-ticket")
    public ResponseEntity<?> generateHallTicket(@PathVariable Long id) {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.MANAGE_APPLICATIONS);
        if (err != null) return err;
        return ResponseEntity.ok(eas.generateHallTicket(id));
    }

    @PostMapping("/exam-applications/batch-generate-hall-tickets")
    public ResponseEntity<?> batchGenerateHallTickets() {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.MANAGE_APPLICATIONS);
        if (err != null) return err;
        eas.batchGenerateHallTickets();
        return ResponseEntity.ok("Batch hall ticket generation process completed.");
    }
}
