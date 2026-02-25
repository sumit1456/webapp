package com.rasthrabhasha.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rasthrabhasha.student.dto.StudentDTO;
import com.rasthrabhasha.student.dto.StudentFilterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
public class StudentController {

	@Autowired
	StudentService sr;

	@GetMapping("/getStudent")
	public StudentDTO getStudent(long id) {
		return sr.getStudentDTO(id);
	}

	@GetMapping("/getAllStudents")
	public List<StudentDTO> getAllStudents() {
		return sr.getAllStudentsDTOs();
	}

	@PostMapping("/addStudent")
	public ResponseEntity<StudentDTO> addStudent(
			@RequestParam Long schoolId, // Matches ?schoolId= in frontend
			@RequestBody Student st) {
		return createStudent(schoolId, st);
	}

	@PostMapping("/students")
	public ResponseEntity<StudentDTO> createStudent(
			@RequestParam Long schoolId,
			@RequestBody Student st) {

		// Save via Service (assuming sr is your service/repository)
		StudentDTO dto = sr.addStudent(schoolId, st);

		// Returns 201 Created with the saved object
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@GetMapping("getStudentById")
	public ResponseEntity<StudentDTO> getStudentById(@RequestParam long student_id) {

		return ResponseEntity.status(HttpStatus.OK).body(sr.findStudentById(student_id));

	}

	@GetMapping("/students")
	public Page<StudentDTO> searchStudents(
			StudentFilterDTO filter,
			Pageable pageable) {
		
		
		return sr.searchStudents(filter, pageable);
	}

}
