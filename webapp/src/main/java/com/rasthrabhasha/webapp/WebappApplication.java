package com.rasthrabhasha.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@ComponentScan(basePackages = {
    "com.rasthrabhasha.student",
    "com.rasthrabhasha.exam",
    "com.rasthrabhasha.application",
    "com.rasthrabhasha.result",
    "com.rasthrabhasha.webapp",
    "com.rasthrabhasha.admin",
})
@EnableJpaRepositories(basePackages = {
    "com.rasthrabhasha.student",
    "com.rasthrabhasha.exam",
    "com.rasthrabhasha.application",
    "com.rasthrabhasha.result",
    "com.rasthrabhasha.admin",
})
@EntityScan(basePackages = {
    "com.rasthrabhasha.student",
    "com.rasthrabhasha.exam",
    "com.rasthrabhasha.application",
    "com.rasthrabhasha.result",
    "com.rasthrabhasha.admin",
})

public class WebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}

}
