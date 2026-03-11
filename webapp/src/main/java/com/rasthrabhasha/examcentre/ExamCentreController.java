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
			@RequestBody Map<String, String> payload) { // Use Map here
		return createExamCentre(regionId, payload);
	}

	@PostMapping("/exam-centres")
	public ResponseEntity<ExamCentreDTO> createExamCentre(
			@RequestParam Long regionId,
			@RequestBody Map<String, String> payload) {
		String code = payload.get("centreCode");
		String name = payload.get("centreName");

		ExamCentre examCentre = new ExamCentre();
		examCentre.setCentreCode(code);
		examCentre.setCentreName(name);

		// 3. Save as usual
		ExamCentreDTO savedCentre = examCentreService.addExamCentre(regionId, examCentre);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCentre);
	}

	@GetMapping("/getAllExamCentres")
	public ResponseEntity<List<ExamCentreDTO>> getAllExamCentres() {
		return ResponseEntity.ok(examCentreService.getAllExamCentresDTOs());
	}

	@GetMapping("/exam-centres")
	public PageResponse<ExamCentreDTO> searchExamCentres(
			ExamCentreFilterDTO filter,
			Pageable pageable) {
		return examCentreService.searchExamCentres(filter, pageable);
	}

	@PutMapping("/exam-centres/{id}")
	public ResponseEntity<ExamCentreDTO> updateExamCentre(@PathVariable long id, @RequestBody ExamCentre examCentre) {
		return ResponseEntity.ok(examCentreService.updateExamCentre(id, examCentre));
	}

	@DeleteMapping("/exam-centres/{id}")
	public ResponseEntity<Void> deleteExamCentre(@PathVariable long id) {
		examCentreService.deleteExamCentre(id);
		return ResponseEntity.noContent().build();
	}

}
