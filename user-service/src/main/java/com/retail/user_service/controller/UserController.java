package com.retail.user_service.controller;


import com.retail.user_service.context.UserContext;
import com.retail.user_service.dto.request.LoginRequest;
import com.retail.user_service.dto.request.UserRequest;
import com.retail.user_service.dto.response.UserResponse;
import com.retail.user_service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("v1/user")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<UserResponse> getUserInfo(@RequestHeader("X-USER-ID") UUID userId){
        return ResponseEntity.ok(userService.getUserDetails(userId));
    }

    @PostMapping("")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request){
        log.info("Creating user");
        return ResponseEntity.ok(userService.createUser(request));
    }

    @PutMapping("")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest request){
        return ResponseEntity.ok(userService.updateUser(UserContext.getUserId(), request));
    }

    @PostMapping("/token")
    public  ResponseEntity<?> generateToken(@Validated @RequestBody LoginRequest loginRequest){
        log.info("Generating token");
        return ResponseEntity.ok(userService.loginAndGenerateToken(loginRequest));
    }
}
