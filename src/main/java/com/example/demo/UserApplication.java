package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // Marks this class as a Spring Boot application entry point
public class UserApplication {

	// Main method that runs the Spring Boot application
	public static void main(String[] args) {
		// Launches the Spring Boot application
		SpringApplication.run(UserApplication.class, args);
	}
}
