package com.example.employee.service;


import com.example.employee.model.dto.UserDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration-seconds}")
    private long jwtExpirationSeconds;

    @Value("${jwt.issuer}")
    private String jwtIssuer;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UserDTO user) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());

        Date issuedAt = new Date();
        Date expiration = new Date(
                System.currentTimeMillis() + jwtExpirationSeconds * 1000
        );

        return Jwts.builder()
                .setIssuer(jwtIssuer)
                .setClaims(claims)
                .setSubject(user.getGmail())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

