package com.doug.tutorial.jwtdemo.security.token;

import com.doug.tutorial.jwtdemo.core.model.ApplicationUser;
import com.doug.tutorial.jwtdemo.core.property.JwtConfiguration;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenCreator {

    private final JwtConfiguration jwtConfiguration;

    @SneakyThrows
    public SignedJWT createSignedJwt(Authentication auth) {
        log.info("Starting JWT creation (signed and encrypted -nimbus jose-)!");
        ApplicationUser appUser = (ApplicationUser) auth.getPrincipal();
        JWTClaimsSet claimSet = createClaims(auth, appUser);
        KeyPair rsaKeyPair = generateKeyPair();

        log.info("Building JWK from the RSA Keys");
        JWK jwk = new RSAKey.Builder((RSAPublicKey) rsaKeyPair.getPublic()).keyID(UUID.randomUUID().toString()).build();
        SignedJWT signedJWT = new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.RS256).jwk(jwk).type(JOSEObjectType.JWT).build(), claimSet);

        log.info("Signing the Token with the private Key!");
        RSASSASigner signer = new RSASSASigner(rsaKeyPair.getPrivate());
        signedJWT.sign(signer);

        log.info("Serialized Token '{}'", signedJWT.serialize());

        return signedJWT;
    }

    private JWTClaimsSet createClaims(Authentication auth, ApplicationUser appUser) {

        return new JWTClaimsSet.Builder().subject(appUser.getUsername())
                .claim("authorities", auth.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .claim("userId",appUser.getId())
                .issuer("http://wwww.doug.com")
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + (jwtConfiguration.getExpiration() * 1000))).build();
    }
    @SneakyThrows
    private KeyPair generateKeyPair() {
        log.info("Generating KeyPair RSA 2048 bits");

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.genKeyPair();
    }

    @SneakyThrows
    public String encrypToken(SignedJWT signedJWT) {

        JWEObject jweObject = null;
        try {
            log.info("starting jwt encryption!");
            DirectEncrypter directEncrypter = new DirectEncrypter(jwtConfiguration.getKey().getBytes());
            jweObject = new JWEObject(new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256).contentType("JWT").build(), new Payload(signedJWT));

            log.info("Encrypting token with system's private Key");
            jweObject.encrypt(directEncrypter);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        return jweObject.serialize();
    }
}
