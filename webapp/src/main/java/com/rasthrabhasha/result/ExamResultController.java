package com.rasthrabhasha.result;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rasthrabhasha.dto.ExamResultDTO;

@RestController
public class ExamResultController {

        @Autowired
        ExamResultService es;

        @PostMapping("/addExamResult")
        public ResponseEntity<ExamResultDTO> addExamResult(@RequestBody ExamResult er) {
           
                
                return ResponseEntity.ok().body(es.createResult(er));
        }

        @GetMapping("/getExamResult")
        public ExamResultDTO getExamResult(@RequestParam long applicationId) {
                return es.getResultDTO(applicationId);
        }

        // Endpoint for Admin Dashboard
        @GetMapping("/getAllResults")
        public List<ExamResultDTO> getAllResults() {
                return es.getAllResultsDTOs();
        }

        // --- ADD THIS NEW ENDPOINT FOR STUDENT DASHBOARD ---
        @GetMapping("/getStudentResults")
        public List<ExamResultDTO> getStudentResults(@RequestParam long studentId) {
                return es.getStudentResultsDTOs(studentId);
        }

}