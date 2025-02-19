package com.example.backend_tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendTutorialApplication.class, args);
		System.out.println("Spring boot is runnung on localhost:3000");
	}

}
