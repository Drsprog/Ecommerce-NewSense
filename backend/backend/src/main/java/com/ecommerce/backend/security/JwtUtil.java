package com.ecommerce.backend.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
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

        public String generateToken(String username, String role) {
                return Jwts.builder()
                                .setSubject(username)
                                .claim("role", role)
                                .setIssuedAt(new Date())
                                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                                .signWith(key, SignatureAlgorithm.HS256)
                                .compact();
        }

        public String getUsernameFromToken(String token) {
                return getAllClaims(token).getSubject();
        }

        // Obtener rol
        public String getRoleFromToken(String token) {
                return getAllClaims(token).get("role", String.class);
        }

        private Claims getAllClaims(String token) {
                return Jwts.parserBuilder()
                                .setSigningKey(key)
                                .build()
                                .parseClaimsJws(token)
                                .getBody();
        }
}