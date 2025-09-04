package com.retail.cart_service.controller;

import com.retail.cart_service.dto.common.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerErrorHandler {

    private static final String CODE_RUNTIME_EXCEPTION = "code.runtime.exception";
    private static final String MESSAGE = "Error occurred while processing request";

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> handleRuntimeException(RuntimeException e){
        log.error("Error:", e);
        var error = new ErrorDto(CODE_RUNTIME_EXCEPTION, MESSAGE, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleException(MethodArgumentNotValidException e){
        var error = new ErrorDto(CODE_RUNTIME_EXCEPTION, MESSAGE, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
