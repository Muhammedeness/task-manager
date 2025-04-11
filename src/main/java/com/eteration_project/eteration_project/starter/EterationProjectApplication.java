package com.eteration_project.eteration_project.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.eteration_project.eteration_project"})
@ComponentScan(basePackages = {"com.eteration_project.eteration_project"})
@EnableJpaRepositories(basePackages = {"com.eteration_project.eteration_project"})
@SpringBootApplication
public class EterationProjectApplication {

	public static void main(String[] args) {

		SpringApplication.run(EterationProjectApplication.class, args);
	}

}
