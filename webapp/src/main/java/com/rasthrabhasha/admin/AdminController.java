package com.rasthrabhasha.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rasthrabhasha.admin.dto.DashboardStatsDTO;

@RestController
public class AdminController {

	@GetMapping("/getStats")
	public ResponseEntity<DashboardStatsDTO> getStats() {

		return null;
	}

}
