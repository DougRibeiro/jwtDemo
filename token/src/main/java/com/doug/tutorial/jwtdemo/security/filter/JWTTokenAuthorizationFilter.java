package com.doug.tutorial.jwtdemo.security.filter;

import com.doug.tutorial.jwtdemo.core.property.JwtConfiguration;
import com.doug.tutorial.jwtdemo.security.token.TokenConverter;
import com.doug.tutorial.jwtdemo.security.util.SecurityContextUtil;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class JWTTokenAuthorizationFilter extends OncePerRequestFilter {
    protected final JwtConfiguration jwtConfiguration;
    protected final TokenConverter tokenConverter;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse,@NotNull  FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader(jwtConfiguration.getHeader().getName());

        if(header==null || !header.startsWith(jwtConfiguration.getHeader().getPrefix())){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        String token = header.replace(jwtConfiguration.getHeader().getPrefix(),"").trim();

        SecurityContextUtil.setSecurityContext(StringUtils.equalsIgnoreCase("signed",jwtConfiguration.getType())?validate(token):decryptAndValidateToken(token));
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    @SneakyThrows
    private SignedJWT decryptAndValidateToken(String encryptedToken){
        String signedToken = tokenConverter.decryptToken(encryptedToken);
        tokenConverter.validateSignedToken(signedToken);
        return SignedJWT.parse(signedToken);
    }

    @SneakyThrows
    private SignedJWT validate(String signedToken){
        tokenConverter.validateSignedToken(signedToken);
        return SignedJWT.parse(signedToken);
    }
}