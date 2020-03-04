package spring.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class JwtProvider {

    KeyStore keyStore;

    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/keystore/docman.jks");
            keyStore.load(resourceAsStream, "usblaster".toCharArray());
        } catch (IOException | CertificateException | NoSuchAlgorithmException | KeyStoreException e) {
            throw new JwtKeyException("Exception occured while loading keystore");
        }
    }

    public String generateToken() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        return Jwts.builder()
                .setSubject(loggedInUser.getName())
                .signWith(getPrivateKey())
                .compact();
    }

    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("docman", "usblaster".toCharArray());
        } catch (NoSuchAlgorithmException | KeyStoreException | UnrecoverableKeyException e) {
            throw new JwtKeyException("Exception occured while loading keystore");
        }
    }

    public boolean validateToken(String jwt) {
        Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
        return true;
    }

    private PublicKey getPublicKey() {
        try {
            return keyStore.getCertificate("docman").getPublicKey();
        } catch (KeyStoreException e) {
            throw new JwtKeyException("Exception occured while loading keystore");
        }
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getPublicKey())
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
