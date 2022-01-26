package com.example.springcqrsexample.common.interceptor;

import com.example.springcqrsexample.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class AuthenticationException extends CustomException {

    public AuthenticationException() {
        super(HttpStatus.UNAUTHORIZED, "AUTH_ERROR", "not authenticated");
    }
}
