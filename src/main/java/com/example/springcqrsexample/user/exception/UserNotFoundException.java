package com.example.springcqrsexample.user.exception;

import com.example.springcqrsexample.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "USER__NOT_FOUND", "user not found");
    }
}
