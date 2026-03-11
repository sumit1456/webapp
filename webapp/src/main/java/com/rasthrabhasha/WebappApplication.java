package com.rasthrabhasha;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableCaching
public class WebappApplication {
	

    // dummy testing
    public static void main(String[] args) {
        SpringApplication.run(WebappApplication.class, args);
    }
    
    
    @PostConstruct
    public void run() {
    	System.out.println("running");
    	
    }

}
