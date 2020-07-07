package com.doug.tutorial.jwtdemo.security.util;

import com.doug.tutorial.jwtdemo.core.model.ApplicationUser;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
public class SecurityContextUtil {

    public SecurityContextUtil() {
    }

    public static void setSecurityContext(SignedJWT signedJWT) {
        try {
            JWTClaimsSet jwtClaimsSet = signedJWT.getJWTClaimsSet();
            String userName = jwtClaimsSet.getSubject();
            if (userName == null) {
                throw new JOSEException("JWT missing userName!");
            }

            List<String> authorities = jwtClaimsSet.getStringListClaim("authorities");
            ApplicationUser applicationUser = ApplicationUser.builder()
                    .id(jwtClaimsSet.getLongClaim("userId"))
                    .username(userName)
                    .role(String.join(",", authorities))
                    .build();

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(applicationUser,null,createAuthorities(authorities));
            auth.setDetails(signedJWT.serialize());
            SecurityContextHolder.getContext().setAuthentication(auth);


        } catch (Exception ex) {
            log.error("Error setting security Context!", ex);
            SecurityContextHolder.clearContext();
        }
    }

    private static List<SimpleGrantedAuthority> createAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}