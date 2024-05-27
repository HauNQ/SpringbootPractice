package com.example.aspect;

import com.example.aspect.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AspectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AspectApplication.class, args);
	}


	@Bean
	@Autowired
	public CommandLineRunner commandLineRunner(CalculatorService calculatorService) {
		return runner ->{
			calculatorService.add(3,5);
			calculatorService.subtract(3,5);
//			System.out.println("Result : "+result);
		};
	}
}
