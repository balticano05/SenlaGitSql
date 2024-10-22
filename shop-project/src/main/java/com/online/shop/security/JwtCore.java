package com.online.shop.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtCore {

    private Key secret = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    @Value("${expired}")
    private int lifetime;

    public String generateToken(Authentication authentication) {
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + lifetime))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getNameFromJwt(String token) {
        return Jwts.parser().setSigningKey(secret).build().parseSignedClaims(token).getBody().getSubject();
    }

}
