package com.SistemaKanbanGestionProyectos.GestorProyectos.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


import javax.crypto.SecretKey;

@Service

    public class JwtUtilService {

     private final  SecretKey JWT_SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    String jws = Jwts.builder().setSubject("Joe").signWith(JWT_SECRET_KEY).compact();
        public static final long JWT_TOKEN_VALIDITY = 10000 * 600 * 60 * (long) 80; // 8 Horas

        public String extractUsername(String token) {
            return extractClaim(token, Claims::getSubject);
        }

        public Date extractExpiration(String token) {
            return extractClaim(token, Claims::getExpiration);
        }

        public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
            return claimsResolver.apply(extractAllClaims(token));
        }

        private Claims extractAllClaims(String token) {
            return Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody();
        }

        private Boolean isTokenExpired(String token) {
            return extractExpiration(token).before(new Date());
        }

        public String generateToken(UserDetails userDetails) {
            Map<String, Object> claims = new HashMap<>();
            var rol = userDetails.getAuthorities().stream().collect(Collectors.toList()).get(0);
            claims.put("rol", rol);
            String token = createToken(claims, userDetails.getUsername());
            System.out.println("Token generado: " + token);
            return createToken(claims, userDetails.getUsername());
        }

        private String createToken(Map<String, Object> claims, String subject) {

            return Jwts
                    .builder()
                    .setClaims(claims)
                    .setSubject(subject)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                    .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
                    .compact();
        }

        public boolean validateToken(String token, UserDetails userDetails) {
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        }

    }

