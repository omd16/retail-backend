package com.retail.retail_gateway.config;

import com.retail.retail_gateway.config.props.JwtProperties;
import com.retail.retail_gateway.util.JwtUtil;
import io.jsonwebtoken.Claims;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthFilter implements WebFilter {

    private final JwtUtil jwtUtil;
    private final JwtProperties jwtProperties;

    public JwtAuthFilter(JwtUtil jwtUtil, JwtProperties jwtProperties) {
        this.jwtUtil = jwtUtil;
        this.jwtProperties = jwtProperties;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String method = exchange.getRequest().getMethod().name(); // "GET", "POST", etc.
        String path = exchange.getRequest().getPath().value();
        System.out.println("Authenticating path: " + path);


        // Skip authentication for excluded paths
        boolean isPublic = jwtProperties.getPublicPaths().stream()
                .anyMatch(p -> (method.equalsIgnoreCase(p.getMethod()) || method.equalsIgnoreCase("OPTIONS"))
                        && path.startsWith(p.getPath()));
        if (isPublic) {
            System.out.println("Skipping JWT Authentication for: " + path);
            return chain.filter(exchange);
        }

        String token = extractTokenFromHeaderOrCookie(exchange);
        if (token == null) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        Claims claims = jwtUtil.validateToken(token);
        if (claims == null) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String userId = claims.getSubject();

        if (userId == null) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        //set header
        exchange.getRequest().getHeaders().add("X-USER-ID", userId);

        var claimRoles = claims.get("roles");
        List<String> roles = new ArrayList<>();
        if(claimRoles instanceof List<?> roleList){
            for (Object item : roleList){
                roles.add((String) item);
            }
        }


        List<SimpleGrantedAuthority> authorities = roles.stream().map(role -> "ROLE_".concat(role))
                .map(SimpleGrantedAuthority::new).toList();


        Authentication authentication = new UsernamePasswordAuthenticationToken(userId, null, authorities);
        SecurityContext context = new SecurityContextImpl(authentication);

        return chain.filter(exchange)
                .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(context)));
    }

    private String extractTokenFromHeaderOrCookie(ServerWebExchange exchange) {
        // First try header
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        // Then try HttpOnly cookie named "accessToken"
        if (exchange.getRequest().getCookies().getFirst("accessToken") != null) {
            return exchange.getRequest().getCookies().getFirst("accessToken").getValue();
        }

        return null;
    }
}
