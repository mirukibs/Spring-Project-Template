package com.spring.SpringProjectTemplate.configurations.JWT;

import com.spring.SpringProjectTemplate.configurations.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTGenerator {

    // The secret key used for JWT signing.
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    /**
     * Generates a JWT token based on the provided authentication information.
     *
     * @param authentication The authentication object containing user details.
     * @return The generated JWT token.
     */
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }


    /**
     * Extracts the username from a JWT token.
     *
     * @param token The JWT token.
     * @return The username extracted from the token.
     */
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }


    /**
     * Validates the integrity and expiration of a JWT token.
     *
     * @param token The JWT token to validate.
     * @return True if the token is valid; otherwise, false.
     * @throws AuthenticationCredentialsNotFoundException If the token is expired or has an invalid signature.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("JWT verification failed: " + ex.getMessage(), ex);
        }
    }
}