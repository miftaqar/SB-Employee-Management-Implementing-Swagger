package com.ifteqars.emp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class EmployeeManagementSwagger2Application {

	public static void main(String[] args) {
		log.info("EmployeeManagementSwagger2Application Application Started");
		SpringApplication.run(EmployeeManagementSwagger2Application.class, args);
	}

}
