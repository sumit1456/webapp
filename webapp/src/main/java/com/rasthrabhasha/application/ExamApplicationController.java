package com.rasthrabhasha.application;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin; // Import this
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // Allow your Frontend
public class ExamApplicationController {

    @Autowired
    ExamApplicationService eas;

    // We need the Repository to fetch all items easily
    @Autowired
    ExamApplicationRepository examApplicationRepository;

    @PostMapping("/fill-form")
    public ExamApplication fillForm(@RequestBody ExamApplication exapp) {
        return eas.fillForm(exapp);
    }

    @GetMapping("/get-form")
    public ExamApplication getForm(@RequestParam long applicationId, @RequestParam long examNo) {
        return eas.getFormByApplicationIdAndExamNo(applicationId, examNo);
    }

    // --- NEW ENDPOINT FOR ADMIN DASHBOARD ---
    @GetMapping("/getAllApplications")
    public List<ExamApplication> getAllApplications() {
        return examApplicationRepository.findAll();
    }

}