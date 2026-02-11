package com.rasthrabhasha.application;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rasthrabhasha.dto.ExamApplicationDTO;

@RestController
public class ExamApplicationController {

    @Autowired
    ExamApplicationService eas;

    @PostMapping("/fill-form")
    public ResponseEntity<ExamApplicationDTO> fillForm(@RequestBody ExamApplication exapp) {
        return eas.fillForm(exapp);
    }

    @GetMapping("/get-form")
    public ExamApplicationDTO getForm(@RequestParam long applicationId, @RequestParam long examNo) {
        return eas.getApplicationDTO(applicationId, examNo);
    }

    // --- NEW ENDPOINT FOR ADMIN DASHBOARD ---
    @GetMapping("/getAllApplications")
    public List<ExamApplicationDTO> getAllApplications() {
        return eas.getAllApplicationsDTOs();
    }

}