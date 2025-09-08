package com.retail.retail_gateway.controller;

import com.retail.retail_gateway.exception.LoginException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(LoginException.class)
  public ResponseEntity<Map<String, String>> handleCustomException(LoginException ex) {
    return ResponseEntity.status(ex.getStatus()).body(Map.of("message", ex.getMessage()));
  }
}
