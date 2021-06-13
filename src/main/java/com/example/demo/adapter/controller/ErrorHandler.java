package com.example.demo.adapter.controller;

import com.example.demo.adapter.controller.model.ErrorResponse;
import com.example.demo.config.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(NotFoundException exception) {
        var response = ErrorResponse.builder()
                .code(ErrorCode.NOT_FOUND)
                .message("Resource not found").build();
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
    }
}
