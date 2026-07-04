package com.rasthrabhasha.examcentre;

import java.util.List;
import java.util.Map;

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
import com.rasthrabhasha.examcentre.dto.ExamCentreDTO;
import com.rasthrabhasha.examcentre.dto.ExamCentreFilterDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.rasthrabhasha.common.dto.PageResponse;

@RestController
public class ExamCentreController {

	@Autowired
	private ExamCentreService examCentreService;

	@PostMapping("/addExamCentre")
	public ResponseEntity<?> addExamCentre(
			@RequestParam Long regionId,
			@RequestBody Map<String, String> payload) {
		return createExamCentre(regionId, payload);
	}

	@PostMapping("/exam-centres")
	public ResponseEntity<?> createExamCentre(
			@RequestParam Long regionId,
			@RequestBody Map<String, String> payload) {
		ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.MANAGE_CENTRES);
		if (err != null) return err;
		String code = payload.get("centreCode");
		String name = payload.get("centreName");
		ExamCentre examCentre = new ExamCentre();
		examCentre.setCentreCode(code);
		examCentre.setCentreName(name);
		ExamCentreDTO savedCentre = examCentreService.addExamCentre(regionId, examCentre);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCentre);
	}

	@GetMapping("/getAllExamCentres")
	public ResponseEntity<?> getAllExamCentres() {
		ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_CENTRES);
		if (err != null) return err;
		return ResponseEntity.ok(examCentreService.getAllExamCentresDTOs());
	}

	@GetMapping("/exam-centres")
	public ResponseEntity<?> searchExamCentres(
			ExamCentreFilterDTO filter,
			Pageable pageable) {
		ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_CENTRES);
		if (err != null) return err;
		return ResponseEntity.ok(examCentreService.searchExamCentres(filter, pageable));
	}

	@PutMapping("/exam-centres/{id}")
	public ResponseEntity<?> updateExamCentre(@PathVariable long id, @RequestBody ExamCentre examCentre) {
		ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.MANAGE_CENTRES);
		if (err != null) return err;
		return ResponseEntity.ok(examCentreService.updateExamCentre(id, examCentre));
	}

	@DeleteMapping("/exam-centres/{id}")
	public ResponseEntity<?> deleteExamCentre(@PathVariable long id) {
		ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.MANAGE_CENTRES);
		if (err != null) return err;
		examCentreService.deleteExamCentre(id);
		return ResponseEntity.noContent().build();
	}
}
