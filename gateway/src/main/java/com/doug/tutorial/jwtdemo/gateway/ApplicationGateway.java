package com.doug.tutorial.jwtdemo.gateway;

import com.doug.tutorial.jwtdemo.core.property.JwtConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@ComponentScan({"com.doug.tutorial.jwtdemo"})
@EnableConfigurationProperties(value = JwtConfiguration.class)
public class ApplicationGateway {
    public static void main(String[] args) {

        SpringApplication.run(ApplicationGateway.class, args);
    }
}