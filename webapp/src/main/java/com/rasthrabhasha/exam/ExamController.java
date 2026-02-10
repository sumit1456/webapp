package com.rasthrabhasha.exam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rasthrabhasha.dto.ExamDTO;

@CrossOrigin
@RestController
public class ExamController {

	@Autowired
	ExamService es;

	@PostMapping("/addExam")
	public String addExam(@RequestBody Exam exam) {
		es.addExam(exam);
		return "Exam has been added";
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
	public String updateExam(@RequestParam long exam_no, @RequestBody Exam exam) {
		return es.updateExam(exam_no, exam);

	}

}
