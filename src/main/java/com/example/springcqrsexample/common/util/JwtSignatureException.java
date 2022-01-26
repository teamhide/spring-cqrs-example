package com.example.springcqrsexample.common.util;

import com.example.springcqrsexample.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class JwtSignatureException extends CustomException {

    public JwtSignatureException() {
        super(HttpStatus.FORBIDDEN, "JWT_INVALID_SIGNATURE", "jwt signature exception");
    }
}
