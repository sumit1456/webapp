package com.rasthrabhasha.school;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rasthrabhasha.common.enums.Permission;
import com.rasthrabhasha.common.util.PermissionUtils;
import com.rasthrabhasha.school.dto.SchoolDTO;
import com.rasthrabhasha.school.dto.SchoolFilterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.rasthrabhasha.common.dto.PageResponse;

@RestController
public class SchoolController {

	@Autowired
	SchoolService schoolService;

	@PostMapping("/addSchool")
	public ResponseEntity<?> addSchool(
			@RequestParam Long centreId,
			@RequestBody School school) {
		return createSchool(centreId, school);
	}

	@PostMapping("/schools")
	public ResponseEntity<?> createSchool(
			@RequestParam Long centreId,
			@RequestBody School school) {
		ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.MANAGE_SCHOOLS);
		if (err != null) return err;
		SchoolDTO savedSchool = schoolService.addSchool(centreId, school);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedSchool);
	}

	@PutMapping("/schools")
	public ResponseEntity<?> updateSchool(@RequestBody SchoolDTO dto) {
		ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.MANAGE_SCHOOLS);
		if (err != null) return err;
		return ResponseEntity.ok(schoolService.updateSchool(dto));
	}

	@GetMapping("getAllSchools")
	public ResponseEntity<?> getAllSchools() {
		ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_SCHOOLS);
		if (err != null) return err;
		return ResponseEntity.ok(schoolService.getAllSchoolsDTOs());
	}

	@GetMapping("/schools")
	public ResponseEntity<?> searchSchools(
			SchoolFilterDTO filter,
			Pageable pageable) {
		return ResponseEntity.ok(schoolService.searchSchools(filter, pageable));
	}

	@GetMapping("/schools/{id}")
	public ResponseEntity<?> getSchoolById(@PathVariable("id") long id) {
		ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_SCHOOLS);
		if (err != null) return err;
		return ResponseEntity.ok(schoolService.getSchoolById(id));
	}

	@DeleteMapping("/schools/{id}")
	public ResponseEntity<?> deleteSchool(@PathVariable("id") long id) {
		ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.MANAGE_SCHOOLS);
		if (err != null) return err;
		schoolService.deleteSchool(id);
		return ResponseEntity.noContent().build();
	}
}
