package com.training.jdbcdemoforcrud.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtHelper {

    String secret = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";

    //    public String getUserNameFromToken(String token){
//        return getClaimFromToken(token, Claims::getSubject);
//    }
    public String getUserNameFromToken(String token) {
        String username;
        Claims claims = getClaimsFromToken(token);
        username = claims.getSubject();
        System.err.println(username);
        return username;
    }

    public Date getExpirationFromToken(String token) {
        Date expiraton;
        Claims claims = getClaimsFromToken(token);
        expiraton = claims.getExpiration();
        System.err.println(expiraton);
        return expiraton;
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpired(String token){
        Date expiration=getExpirationFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails){

        Map<String,Object> claims=new HashMap<>();
        return doGeneratreToken(claims,userDetails.getUsername());

    }

    public String doGeneratreToken(Map<String,Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+1000*60*5)).signWith(SignatureAlgorithm.HS512,secret).compact();
    }
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username=getUserNameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
