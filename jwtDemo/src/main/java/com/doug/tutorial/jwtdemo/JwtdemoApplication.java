package com.doug.tutorial.jwtdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"com.doug.tutorial.jwtdemo.core.model"})
@EnableJpaRepositories({"com.doug.tutorial.jwtdemo.core.repository"})
public class JwtdemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(JwtdemoApplication.class, args);
	}
}