package com.example.jobportal.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    //private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    //private final long expirationMs = 1000 * 60 * 60; // 10 hours


    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public Key getSigningKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // JWT TOKEN GENERATION FOR SESSIONS
    public String generateToken(String email, String role){
        return Jwts.builder()
            .setSubject(email)
            .claim("role", role)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(getSigningKey())
            .compact();
    }

    // CHECK THE TOKEN IS VALID OR NOT
    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        }
        catch (JwtException e) {
            //e.getMessage();
            return false;
        }
    }

    // EXTRACT EMAILS
    public String extractEmail(String token){
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    // EXTRACT ROLE                                                         
    public String extractRole(String token){
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();

        return claims.get("role", String.class);
    }


}
