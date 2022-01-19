package com.example.springcqrsexample.common.util;

import com.example.springcqrsexample.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class JwtTokenDecodeException extends CustomException {
    public JwtTokenDecodeException() {
        super(HttpStatus.BAD_REQUEST, "TOKEN_DECODE_EXCEPTION", "invalid jwt token");
    }
}
