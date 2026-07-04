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

import com.rasthrabhasha.common.enums.Permission;
import com.rasthrabhasha.common.util.PermissionUtils;
import com.rasthrabhasha.student.dto.StudentDTO;
import com.rasthrabhasha.student.dto.StudentFilterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.rasthrabhasha.common.dto.PageResponse;

@RestController
public class StudentController {

	@Autowired
	StudentService sr;

	@GetMapping("/getStudent")
	public ResponseEntity<?> getStudent(long id) {
		ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_STUDENTS);
		if (err != null) return err;
		return ResponseEntity.ok(sr.getStudentDTO(id));
	}

	@GetMapping("/getAllStudents")
	public ResponseEntity<?> getAllStudents() {
		ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_STUDENTS);
		if (err != null) return err;
		return ResponseEntity.ok(sr.getAllStudentsDTOs());
	}

	@PostMapping("/addStudent")
	public ResponseEntity<?> addStudent(
			@RequestParam Long schoolId,
			@RequestBody Student st) {
		ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.MANAGE_STUDENTS);
		if (err != null) return err;
		return createStudent(schoolId, st);
	}

	@PostMapping("/students")
	public ResponseEntity<?> createStudent(
			@RequestParam Long schoolId,
			@RequestBody Student st) {
		ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.MANAGE_STUDENTS);
		if (err != null) return err;
		StudentDTO dto = sr.addStudent(schoolId, st);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@GetMapping("getStudentById")
	public ResponseEntity<?> getStudentById(@RequestParam long student_id) {
		ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_STUDENTS);
		if (err != null) return err;
		return ResponseEntity.status(HttpStatus.OK).body(sr.findStudentById(student_id));
	}

	@GetMapping("/students")
	public ResponseEntity<?> searchStudents(
			StudentFilterDTO filter,
			Pageable pageable) {
		ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_STUDENTS);
		if (err != null) return err;
		return ResponseEntity.ok(sr.searchStudents(filter, pageable));
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<?> updateStudent(@PathVariable long id, @RequestBody Student student) {
		ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.MANAGE_STUDENTS);
		if (err != null) return err;
		return ResponseEntity.ok(sr.updateStudent(id, student));
	}

	@DeleteMapping("/students/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable long id) {
		ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.MANAGE_STUDENTS);
		if (err != null) return err;
		sr.deleteStudent(id);
		return ResponseEntity.noContent().build();
	}
}
