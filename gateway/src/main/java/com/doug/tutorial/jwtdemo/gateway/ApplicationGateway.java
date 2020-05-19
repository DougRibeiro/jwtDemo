package com.doug.tutorial.jwtdemo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class ApplicationGateway {
    public static void main(String[] args) {

        SpringApplication.run(ApplicationGateway.class, args);
    }
}