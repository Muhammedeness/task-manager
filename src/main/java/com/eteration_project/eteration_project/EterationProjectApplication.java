package com.eteration_project.eteration_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan(basePackages = {"com.eteration_project.eteration_project"})
@ComponentScan(basePackages = {"com.eteration_project.eteration_project"})
@SpringBootApplication
public class EterationProjectApplication {

	public static void main(String[] args) {

		SpringApplication.run(EterationProjectApplication.class, args);
	}

}
