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

import com.rasthrabhasha.exam.dto.ExamDTO;
import com.rasthrabhasha.exam.dto.ExamFilterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@CrossOrigin
@RestController
public class ExamController {

	@Autowired
	ExamService es;

	@PostMapping("/addExam")
	public ResponseEntity<ExamDTO> addExam(@RequestBody Exam exam) {
		return createExam(exam);
	}

	@PostMapping("/exams")
	public ResponseEntity<ExamDTO> createExam(@RequestBody Exam exam) {
		return ResponseEntity.status(HttpStatus.CREATED).body(es.addExam(exam));
	}

	@GetMapping("/getAllExams")
	public List<ExamDTO> getAllExams() {
		return es.getAllExamsDTOs();
	}

	@GetMapping("/getExam")
	public ExamDTO getExam(long id) {
		return es.getExamDTO(id);
	}

	@DeleteMapping("/deleteExam")
	public String deleteExam(@RequestParam long id) {
		es.deleteExam(id);
		return "Exam has been deleted";
	}

	@PutMapping("/updateExam")
	public ResponseEntity<ExamDTO> updateExam(@RequestParam long exam_no, @RequestBody Exam exam) {
		return ResponseEntity.ok(es.updateExam(exam_no, exam));
	}

	@GetMapping("/exams")
	public Page<ExamDTO> searchExams(
			ExamFilterDTO filter,
			Pageable pageable) {
		return es.searchExams(filter, pageable);
	}

}
