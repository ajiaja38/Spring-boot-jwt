package com.springjwt.jwt.utils.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.springjwt.jwt.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

  @Value("${jwt.secret}")
  private String SECRET;

  private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

  public String generateToken(User user) {
    return
      Jwts.builder()
        .subject(user.getId())
        .claim("userId", user.getId())
        .expiration(new Date((System.currentTimeMillis() + EXPIRATION_TIME)))
        .issuedAt(new Date())
        .signWith(this.getSecretKey())
        .compact(); 
  }
  
  private boolean isTokenExpired(Claims claims) {
    return claims.getExpiration().before(new Date());
  }

  private Key getSecretKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
