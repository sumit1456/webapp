package com.rasthrabhasha.examofficer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rasthrabhasha.common.enums.Permission;
import com.rasthrabhasha.common.util.PermissionUtils;
import com.rasthrabhasha.examofficer.dto.ExamOfficerDTO;

@RestController
@RequestMapping("/exam-officer")
public class ExamOfficerController {

	@Autowired
	private ExamOfficerService examOfficerService;

	@PostMapping
	public ResponseEntity<?> createExamOfficer(@RequestBody ExamOfficerDTO dto) {
		ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.MANAGE_USERS);
		if (err != null) return err;
		ExamOfficerDTO created = examOfficerService.createExamOfficer(dto);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getExamOfficerById(@PathVariable long id) {
		ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_DASHBOARD);
		if (err != null) return err;
		ExamOfficerDTO officer = examOfficerService.getExamOfficerById(id);
		if (officer == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(officer);
	}

	@GetMapping
	public ResponseEntity<?> getAllExamOfficers() {
		ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_DASHBOARD);
		if (err != null) return err;
		return ResponseEntity.ok(examOfficerService.getAllExamOfficers());
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateExamOfficer(@PathVariable long id, @RequestBody ExamOfficerDTO dto) {
		ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.MANAGE_USERS);
		if (err != null) return err;
		ExamOfficerDTO updated = examOfficerService.updateExamOfficer(id, dto);
		if (updated == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteExamOfficer(@PathVariable long id) {
		ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.MANAGE_USERS);
		if (err != null) return err;
		boolean deleted = examOfficerService.deleteExamOfficer(id);
		if (!deleted) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}
