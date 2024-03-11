package com.springjwt.jwt.utils.jwt;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
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
        .subject(user.getUsername())
        .claim("userId", user.getId())
        .expiration(new Date((System.currentTimeMillis() + EXPIRATION_TIME)))
        .issuedAt(new Date())
        .signWith(this.getSecretKey())
        .compact(); 
  }
  
  private boolean isTokenExpired(Claims claims) {
    return claims.getExpiration().before(new Date());
  }

  private Claims extractAllClaims(String token) {
    return Jwts
            .parser()
            .verifyWith(getSecretKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
  }

  public String extractUsername(String token) {
    return this.extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
    final Claims claims = this.extractAllClaims(token);
    return claimResolver.apply(claims);
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String userId = this.extractUsername(token);
    return (userId.equals(userDetails.getUsername()) && !isTokenExpired(this.extractAllClaims(token)));
  }

  private SecretKey getSecretKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
