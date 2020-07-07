package com.doug.tutorial.jwtdemo.config;


import com.doug.tutorial.jwtdemo.core.property.JwtConfiguration;
import com.doug.tutorial.jwtdemo.security.config.SecurityTokenConfig;
import com.doug.tutorial.jwtdemo.security.filter.JWTTokenAuthorizationFilter;
import com.doug.tutorial.jwtdemo.security.token.TokenConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityCredentialsConfig extends SecurityTokenConfig {
    private final TokenConverter tokenConverter;

    public SecurityCredentialsConfig(JwtConfiguration jwtConfiguration, TokenConverter tokenConverter) {
        super(jwtConfiguration);
        this.tokenConverter = tokenConverter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(new JWTTokenAuthorizationFilter(jwtConfiguration, tokenConverter), UsernamePasswordAuthenticationFilter.class);
        super.configure(http);
    }

}