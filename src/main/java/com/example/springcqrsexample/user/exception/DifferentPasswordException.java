package com.example.springcqrsexample.user.exception;

import com.example.springcqrsexample.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class DifferentPasswordException extends CustomException {
    public DifferentPasswordException() {
        super(HttpStatus.BAD_REQUEST, "USER__DIFFERENT_PASSWORD", "different password");
    }
}
