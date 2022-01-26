package com.example.springcqrsexample.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomExceptionResponse> handleCustomException(CustomException e) {
        CustomExceptionResponse response = CustomExceptionResponse.builder()
            .errorCode(e.getErrorCode())
            .detail(e.getDetail())
            .build();
        return new ResponseEntity<>(response, e.getStatusCode());
    }
}
