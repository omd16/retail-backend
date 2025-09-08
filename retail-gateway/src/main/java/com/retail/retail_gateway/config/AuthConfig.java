package com.retail.retail_gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;


@Configuration
public class AuthConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())  // No session management
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/auth/login").permitAll()
                        .pathMatchers("/auth/signup").permitAll()
                        .pathMatchers("/test").permitAll()
                        .pathMatchers("/test.html").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/v1/product/**").permitAll()
                        .anyExchange().hasRole("ADMIN")
                );

        http.addFilterBefore(jwtAuthFilter, SecurityWebFiltersOrder.AUTHENTICATION);

        return http.build();
    }
}
