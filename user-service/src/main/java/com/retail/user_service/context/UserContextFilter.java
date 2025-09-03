package com.retail.user_service.context;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class UserContextFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
      HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
      throws ServletException, IOException {

    String userId = req.getHeader("X-USER-ID");

    if (userId != null) {
      UserContext.setUserId(UUID.fromString(userId));
    }

    try {
      filterChain.doFilter(req, res);
    } finally {
      UserContext.clear(); // clean after each request
    }
  }
}
