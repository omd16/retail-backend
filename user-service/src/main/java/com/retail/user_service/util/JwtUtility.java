package com.retail.user_service.util;

import com.retail.user_service.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtility {

  @Value("${app.jwt.secret}")
  private String jwtSecret;

  @Value("${app.jwt.expiration}")
  private Long expiration;

  public String generateToken(User user) {
    Date now = new Date();

    Date expiryTime = new Date(now.getTime() + expiration * 1000L);

    return Jwts.builder()
        .expiration(expiryTime)
        .claim("username", user.getEmail())
        .claim("roles", List.of("USER", "ADMIN")) // TODO: move this role to DB
        .issuedAt(now)
        .subject(user.getId().toString())
        .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)), Jwts.SIG.HS256)
        .compact();
  }
}
