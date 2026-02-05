package com.rasthrabhasha.result;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rasthrabhasha.exception.EntityNotFoundException;

@RestController
public class ExamResultController {

    @Autowired
    ExamResultRepository err;

    @Autowired
    ExamResultService es;

    @PostMapping("/addExamResult")
    public ExamResult addExamResult(@RequestBody ExamResult er) {
        return es.createResult(er);
    }

    @GetMapping("/getExamResult")
    public ExamResult getExamResult(@RequestParam long applicationId) {
        ExamResult er = err.findByApplicationId(applicationId);
        if (er == null) throw new EntityNotFoundException("Invalid Application Id");
        return er;
    }

    // Endpoint for Admin Dashboard
    @GetMapping("/getAllResults")
    public List<ExamResult> getAllResults() {
        return err.findAll();
    }

    // --- ADD THIS NEW ENDPOINT FOR STUDENT DASHBOARD ---
    @GetMapping("/getStudentResults")
    public List<ExamResult> getStudentResults(@RequestParam long studentId) {
        return err.findByStudentId(studentId);
    }

}