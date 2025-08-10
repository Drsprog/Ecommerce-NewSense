package com.ecommerce.backend.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;


@Component
public class JwtUtil {

    private static final String SECRET = "mi_secreto_super_seguro_muy_largo_que_necesitas_1234"; // mínimo 32 chars
    private final Key key;
    private final long EXPIRATION_TIME = 86400000;

 public JwtUtil() {
         System.out.println("Inicializando JwtUtil...");
    this.key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    System.out.println("Clave JWT inicializada correctamente.");
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}