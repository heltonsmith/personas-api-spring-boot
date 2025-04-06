package com.personas.personas_api_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.personas"})
public class PersonasApiSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonasApiSpringBootApplication.class, args);
	}

}
