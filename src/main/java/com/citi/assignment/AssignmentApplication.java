package com.citi.assignment;

import org.springframework.scheduling.annotation.EnableAsync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAsync
public class AssignmentApplication {
	
	private static Logger LOGGER = LoggerFactory.getLogger(AssignmentApplication.class);

	public static void main(String[] args) {
		
		LOGGER.info("Inside Main Method of Spring Boot Application");
		
		SpringApplication.run(AssignmentApplication.class, args);
	}

}
