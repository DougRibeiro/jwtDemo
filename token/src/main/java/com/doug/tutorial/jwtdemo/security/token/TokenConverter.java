package com.doug.tutorial.jwtdemo.security.token;

import com.doug.tutorial.jwtdemo.core.property.JwtConfiguration;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenConverter {

    private final JwtConfiguration jwtConfiguration;

    @SneakyThrows
    public String decryptToken(String encryptedToken) {

        log.info("Decypting token");
        JWEObject jweObject = JWEObject.parse(encryptedToken);
        DirectDecrypter directDecrypter = new DirectDecrypter(jwtConfiguration.getKey().getBytes());
        jweObject.decrypt(directDecrypter);

        log.info("Token Decrypted. Returning decrypted signed token!");
        return jweObject.getPayload().toSignedJWT().serialize();
    }

    @SneakyThrows
    public void validateSignedToken(String signedToken) {
        log.info("Validating signed Token");

        SignedJWT signedJWT = SignedJWT.parse(signedToken);
        RSAKey publicKey = RSAKey.parse(signedJWT.getHeader().getJWK().toJSONObject());

        log.info("public key extracted! Validating token signature!");
        if(!signedJWT.verify(new RSASSAVerifier(publicKey))){
            throw new AccessDeniedException("Invalid Token Signature!");
        }
        log.info("Token signature verified!");
    }
}