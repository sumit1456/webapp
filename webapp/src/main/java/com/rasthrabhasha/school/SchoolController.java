package com.rasthrabhasha.school;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rasthrabhasha.school.dto.SchoolDTO;
import com.rasthrabhasha.school.dto.SchoolFilterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
public class SchoolController {

	@Autowired
	SchoolService schoolService;

	@PostMapping("/addSchool")
	public ResponseEntity<SchoolDTO> addSchool(
			@RequestParam Long centreId,
			@RequestBody School school) {
		return createSchool(centreId, school);
	}

	@PostMapping("/schools")
	public ResponseEntity<SchoolDTO> createSchool(
			@RequestParam Long centreId,
			@RequestBody School school) {

		SchoolDTO savedSchool = schoolService.addSchool(centreId, school);

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(savedSchool);
	}

	@GetMapping("getAllSchools")
	public ResponseEntity<List<SchoolDTO>> getAllSchools() {
		return ResponseEntity.ok(schoolService.getAllSchoolsDTOs());
	}

	@GetMapping("/schools")
	public Page<SchoolDTO> searchSchools(
			SchoolFilterDTO filter,
			Pageable pageable) {
		return schoolService.searchSchools(filter, pageable);
	}
}
