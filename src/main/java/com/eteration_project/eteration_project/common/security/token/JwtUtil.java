package com.eteration_project.eteration_project.common.security.token;

import com.eteration_project.eteration_project.user.model.User;
import com.eteration_project.eteration_project.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    // Secret key for signing JWT token
    @Value("${jwt.secret}")
    private String secretKey;

    // JWT token validity duration (e.g., 1 hour)
    @Value("${jwt.expiration}")
    private long expirationTime;

    private final UserRepository userRepository;

    // Method to generate token
    public String generateToken(String email) {
        User user = userRepository.findUserByEmail(email);
        String name = user.getFirstName() +" "+  user.getLastName();
        return Jwts.builder()
                .setSubject(email)
                .claim("name" , name)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Method to extract email from the token
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractName(String token){
        return extractClaim(token , claims -> claims.get("name" , String.class));
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

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Date getExpiration(String token){
        return  Jwts.parser()
                     .setSigningKey(secretKey)
                     .parseClaimsJws(token)
                     .getBody().getExpiration();
    }
}

