package com.doug.tutorial.jwtdemo;

import com.doug.tutorial.jwtdemo.core.property.JwtConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"com.doug.tutorial.jwtdemo.core.model"})
@EnableJpaRepositories({"com.doug.tutorial.jwtdemo.core.repository"})
@EnableConfigurationProperties(value = JwtConfiguration.class)
@ComponentScan("com.doug.tutorial.jwtdemo")

public class JwtdemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(JwtdemoApplication.class, args);
	}
}