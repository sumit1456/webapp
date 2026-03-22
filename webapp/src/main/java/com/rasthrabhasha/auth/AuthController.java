package com.rasthrabhasha.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rasthrabhasha.auth.dto.LoginRequestDTO;
import com.rasthrabhasha.student.dto.StudentDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthService authService;

	@PostMapping("/student/login")
	public ResponseEntity<?> studentLogin(@RequestBody LoginRequestDTO dto) {
		StudentDTO student = authService.verifyStudent(dto);
		if (student == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("{\"message\": \"Invalid email or password\"}");
		}
		return ResponseEntity.ok(student);
	}

}
