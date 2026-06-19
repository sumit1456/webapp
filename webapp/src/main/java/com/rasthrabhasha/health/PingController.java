package com.rasthrabhasha.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

	@GetMapping("/ping")
	public String ping() {
		return "server is running";
	}

}
