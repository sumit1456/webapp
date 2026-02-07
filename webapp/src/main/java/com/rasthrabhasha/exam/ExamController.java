package com.rasthrabhasha.exam;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rasthrabhasha.exception.EntityNotFoundException;

@CrossOrigin
@RestController
public class ExamController {

	@Autowired
	ExamRepository er;

	@Autowired
	ExamService es;

	@PostMapping("/addExam")
	public String addExam(@RequestBody Exam exam) {
		er.save(exam);
		return "Exam has been added";
	}

	@GetMapping("/getAllExams")
	public List<Exam> getAllExams() {
		return es.getAllExams();
	}

	@GetMapping("/getExam")
	public Exam getExam(long id) {

		Optional<Exam> exam = er.findByExamNo(id);

		System.out.println(exam.get());

		if (exam.isEmpty())
			throw new EntityNotFoundException("Exam No is invalid");

		return exam.get();
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
