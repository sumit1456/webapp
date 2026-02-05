package com.rasthrabhasha.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rasthrabhasha.exception.EntityNotFoundException;

@RestController
public class StudentController {

	@Autowired
	StudentService sr;

	@Autowired
	StudentRepository str;

	@GetMapping("/getStudent")
	public Student getStudent(long id) {

		Optional<Student> student = str.findById(id);
		if (student.isEmpty()) {
			throw new EntityNotFoundException("Invalid Student id");
		}

		return student.get();
	}

	@GetMapping("/getAllStudents")
	public List<Student> getAllStudents() {
		return sr.getAllStudents();
	}

	@PostMapping("/addStudent")
	public String addStudent(@RequestBody Student st) {
		return sr.addStudent(st);

	}

}
