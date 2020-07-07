package com.doug.tutorial.jwtdemo.security.config;

import com.doug.tutorial.jwtdemo.core.property.JwtConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@RequiredArgsConstructor
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {

    protected final JwtConfiguration jwtConfiguration;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint((req, resp, e) -> resp.sendError(SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .antMatchers(jwtConfiguration.getLoginUrl()).permitAll()
                .antMatchers("/v1/admin/demo/**").hasRole("ADMIN")
                //.antMatchers("/auth/user/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/auth/user/**").permitAll()
                .anyRequest().authenticated();
    }
}
