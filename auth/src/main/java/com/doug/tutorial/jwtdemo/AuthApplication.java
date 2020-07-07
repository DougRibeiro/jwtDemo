package com.doug.tutorial.jwtdemo;

import com.doug.tutorial.jwtdemo.core.property.JwtConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"com.doug.tutorial.jwtdemo.core.model"})
@EnableJpaRepositories({"com.doug.tutorial.jwtdemo.core.repository"})
@EnableEurekaClient
@ComponentScan({"com.doug.tutorial.jwtdemo"})
@EnableConfigurationProperties(value = JwtConfiguration.class)
public class AuthApplication {
	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}
}