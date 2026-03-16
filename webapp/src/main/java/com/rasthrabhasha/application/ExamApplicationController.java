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

import com.rasthrabhasha.application.dto.ExamApplicationDTO;
import com.rasthrabhasha.application.dto.ExamApplicationFilterDTO;

@RestController
public class ExamApplicationController {

    @Autowired
    ExamApplicationService eas;

    // Create / submit a new application (or update if already exists for same
    // student+exam)
    @PostMapping("/exam-applications")
    public ResponseEntity<ExamApplicationDTO> createApplication(@RequestBody ExamApplicationDTO dto) {
    	System.out.println(dto.getExamNo());
    	System.out.println(dto.getStudentId());
        return eas.fillForm(dto);
    }

    // Get a single application by applicationId and examNo
    @GetMapping("/exam-applications-byId")
    public ExamApplicationDTO getForm(@RequestParam long applicationId, @RequestParam long examNo) {
        return eas.getApplicationDTO(applicationId, examNo);
    }

    // legacy
    // Get all applications (for admin dashboard)
    @GetMapping("/getAllApplications/{id}")
    public List<ExamApplicationDTO> getAllApplications() {
        return eas.getAllApplicationsDTOs();
    }

    // Search/filter applications with pagination
    @GetMapping("/exam-applications")
    public PageResponse<ExamApplicationDTO> searchApplication(
            ExamApplicationFilterDTO filter,
            Pageable pageable) {
        return eas.searchApplications(filter, pageable);
    }

    // Update an existing application's formData and/or status
    @PutMapping("/exam-applications/{id}")
    public ExamApplicationDTO updateApplication(
            @PathVariable long id,
            @RequestBody ExamApplicationDTO dto) {
        return eas.updateApplication(id, dto);
    }

    // Delete an application and its associated results
    @DeleteMapping("/exam-applications/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable long id) {
        eas.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }

}
