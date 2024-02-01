package com.training.jdbcdemoforcrud.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtHelper {

    String secret = "welcome-to-jdbcdemoforcrud-demo-project-this-string-is-my-secret-key-used-to-sign-my-jwt-token";
    Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    public String getUserNameFromToken(String token) {
        String username;
        Claims claims = getClaimsFromToken(token);
        username = claims.getSubject();
        System.err.println(username);
        return username;
    }

    public Date getExpirationFromToken(String token) {
        Date expiration;
        Claims claims = getClaimsFromToken(token);
        expiration = claims.getExpiration();
        System.err.println(expiration);
        return expiration;
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpired(String token){
        Date expiration=getExpirationFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails){

        Map<String,Object> claims=new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());

    }

    public String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)).signWith(key, SignatureAlgorithm.HS512).compact();
    }
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username=getUserNameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
