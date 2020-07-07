package com.doug.tutorial.jwtdemo.gateway.security.config;

import com.doug.tutorial.jwtdemo.core.property.JwtConfiguration;
import com.doug.tutorial.jwtdemo.security.filter.JWTTokenAuthorizationFilter;
import com.doug.tutorial.jwtdemo.security.token.TokenConverter;
import com.doug.tutorial.jwtdemo.security.util.SecurityContextUtil;
import com.netflix.zuul.context.RequestContext;
import com.nimbusds.jwt.SignedJWT;
import lombok.SneakyThrows;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

public class GatewayTokenAuthorizationFilter extends JWTTokenAuthorizationFilter {
    public GatewayTokenAuthorizationFilter(JwtConfiguration jwtConfiguration, TokenConverter tokenConverter) {
        super(jwtConfiguration, tokenConverter);
    }

    @Override
    @SneakyThrows
    protected void doFilterInternal(@NotNull HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse, @NotNull FilterChain filterChain) {
        String header = httpServletRequest.getHeader(jwtConfiguration.getHeader().getName());

        if(header==null || !header.startsWith(jwtConfiguration.getHeader().getPrefix())){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        String token = header.replace(jwtConfiguration.getHeader().getPrefix(),"").trim();
        String signedToken = tokenConverter.decryptToken(token);

        tokenConverter.validateSignedToken(signedToken);

        SecurityContextUtil.setSecurityContext(SignedJWT.parse(signedToken));


        if(jwtConfiguration.getType().equalsIgnoreCase("signed")){
            RequestContext.getCurrentContext().addZuulRequestHeader("Authorization",jwtConfiguration.getHeader().getPrefix()+signedToken);
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}