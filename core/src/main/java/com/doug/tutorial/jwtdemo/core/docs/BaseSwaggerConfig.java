package com.doug.tutorial.jwtdemo.core.docs;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class BaseSwaggerConfig {

    private final String basePackages;

    public BaseSwaggerConfig(String basePackages) {
        this.basePackages = basePackages;
    }

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage(basePackages))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData(){
        return new ApiInfoBuilder().title("Jwt Demo App")
                .description("Signed and ecrypted token Demo!").version("1.0")
                .contact(new Contact("Douglas","http://nourl.com","doug.ribeiro.dub@gmail.com"))
                .license("Open")
                .licenseUrl("http://nourl.com").build();
    }
}
