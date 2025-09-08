package com.retail.retail_gateway.controller;

import com.retail.retail_gateway.client.UserServiceClient;
import com.retail.retail_gateway.dto.LoginRequest;
import com.retail.retail_gateway.dto.LoginResponse;
import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class LoginController {

    private final UserServiceClient userServiceClient;

    public LoginController(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @PostMapping("/login")
    public Mono<Map<String, String>> login(@RequestBody LoginRequest request,
                                           ServerHttpResponse response) {
        return userServiceClient.loginRequest(request)
                .map(loginResponse -> {
                    String token = loginResponse.getAccessToken();

                    ResponseCookie cookie = ResponseCookie.from("accessToken", token)
                            .httpOnly(true)
                            .secure(false) // set true in prod with HTTPS
                            .path("/")
                            .maxAge(3600)
                            .sameSite("Strict")
                            .build();

                    response.addCookie(cookie);

                    return Map.of(
                            "message", "Login successful"
                    );
                });
    }

    @PostMapping("/logout")
    public Mono<ResponseEntity<Map<String, String>>> logout(ServerHttpResponse response) {
        ResponseCookie deleteCookie = ResponseCookie.from("accessToken", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(Duration.ZERO)
                .sameSite("Strict")
                .build();

        response.addCookie(deleteCookie);

        return Mono.just(ResponseEntity.ok(Map.of("message", "Logged out successfully")));
    }
}
