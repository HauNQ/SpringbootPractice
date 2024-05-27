package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ConfigActuatorApplication {
	@Value("${person.name}")
	private String name;

	@Value("${person.age}")
	private int age;

	public static void main(String[] args) {
		SpringApplication.run(ConfigActuatorApplication.class, args);
	}

	@GetMapping("/home")
	public String home() {
		return name +" : "+age;
	}
}
