package com.rasthrabhasha.result;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rasthrabhasha.result.dto.ExamResultDTO;
import com.rasthrabhasha.result.dto.ExamResultFilterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.rasthrabhasha.common.dto.PageResponse;

@RestController
public class ExamResultController {

        @Autowired
        ExamResultService es;


        @PostMapping("/exam-results")
        public ResponseEntity<ExamResultDTO> createExamResult(
            @RequestBody ExamResult er, 
            @RequestParam("applicationId") long applicationId
        ) {
            com.rasthrabhasha.application.ExamApplication app = new com.rasthrabhasha.application.ExamApplication();
            app.setApplicationId(applicationId);
            er.setApplication(app);
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

        @GetMapping("/exam-results")
        public PageResponse<ExamResultDTO> searchExamResults(
                        ExamResultFilterDTO filter,
                        Pageable pageable) {
                return es.searchExamResults(filter, pageable);
        }

}