package com.rasthrabhasha.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rasthrabhasha.auth.dto.LoginRequestDTO;
import com.rasthrabhasha.auth.dto.LoginResponseDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO dto,
            @RequestParam(defaultValue = "STUDENT") String role) {
        log.info("[Backend Auth] Login request received for username: '{}', role: '{}'", dto.getUsername(), role);
        LoginResponseDTO response = authService.login(dto, role);
        if (response == null) {
            log.warn("[Backend Auth] Login failed (Invalid credentials) for username: '{}', role: '{}'", dto.getUsername(), role);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"message\": \"Invalid credentials\"}");
        }
        log.info("[Backend Auth] Login successful for username: '{}', role: '{}', userId: {}", response.getUsername(), response.getRole(), response.getUserId());
        return ResponseEntity.ok(response);
    }
}
