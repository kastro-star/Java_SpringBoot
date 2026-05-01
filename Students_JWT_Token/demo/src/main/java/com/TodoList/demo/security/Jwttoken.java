package com.TodoList.demo.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class Jwttoken {
    private final String SECRET ="KASTR0 - jwt sprinboot secure filter authentication for webpages";
    private final long expiration =1000*60*60;
    private final Key secretKey =Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));


    //JWT token generation
    public String tokengeneration(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ expiration) )
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }


    //if you passes the token it returns the email
    public String extractemail(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


    //JWT validate tokens
    public boolean validatetoken(String token){
            try{
                extractemail(token);
                return true;
            }catch (JwtException exception){
                return false;
            }
    }


}
