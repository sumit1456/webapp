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

import com.rasthrabhasha.region.Region;

import com.rasthrabhasha.dto.ExamCentreDTO;

@RestController
public class ExamCentreController {

	@Autowired
	private ExamCentreService examCentreService;

	@PostMapping("/addExamCentre")
	public ResponseEntity<?> addExamCentre(
			@RequestParam Long regionId,
			@RequestBody Map<String, String> payload) { // Use Map here
		// 1. Manually extract the fields from the JSON Map
		System.out.println(payload);
		System.out.println("This is a region id " + regionId);
		String code = payload.get("centreCode");
		String name = payload.get("centreName");
		// DEBUG: Verify the data in your console
		System.out.println("DEBUG: Received Map payload: " + payload);
		System.out.println("DEBUG: Extracted -> Code: " + code + ", Name: " + name);
		// 2. Create the ExamCentre object manually
		ExamCentre examCentre = new ExamCentre();
		examCentre.setCentreCode(code);
		examCentre.setCentreName(name);

		// 3. Save as usual
		ExamCentre savedCentre = examCentreService.addExamCentre(regionId, examCentre);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCentre);
	}

	@GetMapping("/getAllExamCentres")
	public ResponseEntity<List<ExamCentreDTO>> getAllExamCentres() {
		return ResponseEntity.ok(examCentreService.getAllExamCentresDTOs());
	}

}
