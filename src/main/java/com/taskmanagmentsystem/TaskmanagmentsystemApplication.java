package com.taskmanagmentsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class TaskmanagmentsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagmentsystemApplication.class, args);
	}

}
