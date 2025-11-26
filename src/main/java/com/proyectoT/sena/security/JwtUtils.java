package com.proyectoT.sena.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private final String SECRET = "MI_SECRETO_SUPER_SEGURO_12345";
    private final long EXPIRATION = 86400000; // 24 horas

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // TOKEN SIMPLE PARA ACTIVACIÓN Y RESET
    public String generateSimpleToken() {
        return Long.toHexString(Double.doubleToLongBits(Math.random())).substring(0, 20);
    }
}


