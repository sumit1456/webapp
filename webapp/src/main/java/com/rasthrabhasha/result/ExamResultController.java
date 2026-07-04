package com.rasthrabhasha.result;

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

import com.rasthrabhasha.common.enums.Permission;
import com.rasthrabhasha.common.util.PermissionUtils;
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
        public ResponseEntity<?> createExamResult(
            @RequestBody ExamResult er,
            @RequestParam("applicationId") long applicationId
        ) {
                ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.PUBLISH_RESULTS);
                if (err != null) return err;
                com.rasthrabhasha.application.ExamApplication app = new com.rasthrabhasha.application.ExamApplication();
                app.setApplicationId(applicationId);
                er.setApplication(app);
                return ResponseEntity.ok().body(es.createResult(er));
        }

        @GetMapping("/getExamResult")
        public ResponseEntity<?> getExamResult(@RequestParam long applicationId) {
                ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_RESULTS);
                if (err != null) return err;
                return ResponseEntity.ok(es.getResultDTO(applicationId));
        }

        @GetMapping("/getAllResults")
        public ResponseEntity<?> getAllResults() {
                ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_RESULTS);
                if (err != null) return err;
                return ResponseEntity.ok(es.getAllResultsDTOs());
        }

        @GetMapping("/getStudentResults")
        public ResponseEntity<?> getStudentResults(@RequestParam long studentId) {
                ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_RESULTS);
                if (err != null) return err;
                return ResponseEntity.ok(es.getStudentResultsDTOs(studentId));
        }

        @GetMapping("/exam-results")
        public ResponseEntity<?> searchExamResults(
                        ExamResultFilterDTO filter,
                        Pageable pageable) {
                ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_RESULTS);
                if (err != null) return err;
                return ResponseEntity.ok(es.searchExamResults(filter, pageable));
        }

        @PutMapping("/exam-results/{id}")
        public ResponseEntity<?> updateExamResult(@PathVariable long id, @RequestBody ExamResult er) {
                ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.PUBLISH_RESULTS);
                if (err != null) return err;
                return ResponseEntity.ok(es.updateResult(id, er));
        }

        @DeleteMapping("/exam-results/{id}")
        public ResponseEntity<?> deleteExamResult(@PathVariable long id) {
                ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.PUBLISH_RESULTS);
                if (err != null) return err;
                es.deleteResult(id);
                return ResponseEntity.noContent().build();
        }
}
