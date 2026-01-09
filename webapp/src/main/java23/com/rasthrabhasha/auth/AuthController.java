package com.rasthrabhasha.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rasthrabhasha.dto.LoginRequestDTO;




@RestController
@RequestMapping("/auth")




public class AuthController {
	
	
	@Autowired
	AuthService authService;

	

	
	
	
}
	

