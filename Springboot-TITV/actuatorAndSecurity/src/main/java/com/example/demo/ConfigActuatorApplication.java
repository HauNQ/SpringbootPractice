package com.example.demo;

import com.example.demo.service.EmailService;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ConfigActuatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigActuatorApplication.class, args);
	}


}
