package com.example.springcqrsexample.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {

    private HttpStatus statusCode;
    private String errorCode;
    private String detail;

    public CustomException(HttpStatus statusCode, String errorCode, String detail) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.detail = detail;
    }
}
