package com.example.springcqrsexample.common.util;

import com.example.springcqrsexample.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class JwtExpiredException extends CustomException {

    public JwtExpiredException() {
        super(HttpStatus.FORBIDDEN, "JWT_EXPIRED", "expired token");
    }
}
