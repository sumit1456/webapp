package com.rasthrabhasha.exam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.rasthrabhasha.exam.dto.ExamDTO;
import com.rasthrabhasha.exam.dto.ExamFilterDTO;
import com.rasthrabhasha.common.dto.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@CrossOrigin
@RestController
public class ExamController {

	@Autowired
	ExamService es;

	@PostMapping("/exams")
	public ResponseEntity<ExamDTO> createExam(@RequestBody Exam exam) {
		return ResponseEntity.status(HttpStatus.CREATED).body(es.addExam(exam));
	}

	@GetMapping("/exams/all")
	public ResponseEntity<List<ExamDTO>> getAllExams() {
		return ResponseEntity.ok(es.getAllExamsDTOs());
	}

	@GetMapping("/exams")
	public ResponseEntity<PageResponse<ExamDTO>> searchExams(
			ExamFilterDTO filter,
			Pageable pageable) {
		return ResponseEntity.ok(es.searchExams(filter, pageable));
	}

	@GetMapping("/exams/{id}")
	public ResponseEntity<ExamDTO> getExam(@PathVariable long id) {
		return ResponseEntity.ok(es.getExamDTO(id));
	}

	@PutMapping("/exams/{id}")
	public ResponseEntity<ExamDTO> updateExam(@PathVariable long id, @RequestBody Exam exam) {
		return ResponseEntity.ok(es.updateExam(id, exam));
	}

	@DeleteMapping("/exams/{id}")
	public ResponseEntity<Void> deleteExam(@PathVariable long id) {
		es.deleteExam(id);
		return ResponseEntity.noContent().build();
	}

}