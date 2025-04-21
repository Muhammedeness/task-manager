package com.eteration_project.eteration_project.common.Token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    // Secret key for signing JWT token
    @Value("${jwt.secret}")
    private String secretKey;

    // JWT token validity duration (e.g., 1 hour)
    @Value("${jwt.expiration}")
    private long expirationTime;

    // Method to generate token
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)  // Set the email as the subject of the token
                .setIssuedAt(new Date())  // Set the issue date
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))  // Set expiration time
                .signWith(SignatureAlgorithm.HS256, secretKey)  // Sign with HMAC SHA-256 algorithm and the secret key
                .compact();
    }

    // Method to extract email from the token
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Method to extract a claim from the token
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Method to extract all claims from the token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)  // Set the signing key to validate the token
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Method to validate the token (check if the token is expired and matches the user details)
    public boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Method to check if the token is expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Method to extract the expiration date from the token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}

