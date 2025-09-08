package com.retail.retail_gateway.config;

import com.retail.retail_gateway.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.*;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleFilter extends AbstractGatewayFilterFactory<RoleFilter.Config> {

    private final JwtUtil jwtUtil;

    public RoleFilter(JwtUtil jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String token = extractTokenFromHeaderOrCookie(exchange);
            if (token == null) return unauthorized(exchange);

            try {
                Claims claims = jwtUtil.validateToken(token);

                if (checkRole(claims, config.getRoles())) {
                    return chain.filter(exchange);
                }
            } catch (Exception e) {
                return unauthorized(exchange);
            }

            return unauthorized(exchange);
        };
    }

    private static boolean checkRole(Claims claims, List<String> allowedRoles) {
        if(allowedRoles.isEmpty()){
            return true;
        }
        var claimRoles = claims.get("roles");
        List<String> roles = new ArrayList<>();
        if(claimRoles instanceof List<?> roleList){
            for (Object item : roleList){
                roles.add((String) item);
            }
        }
        return roles.stream().anyMatch(allowedRoles::contains);
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

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
        return exchange.getResponse().setComplete();
    }


    public static class Config{

        private List<String> roles;

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }
    }
}


