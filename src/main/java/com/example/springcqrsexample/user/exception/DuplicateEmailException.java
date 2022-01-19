package com.example.springcqrsexample.user.exception;

import com.example.springcqrsexample.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends CustomException {
    public DuplicateEmailException() {
        super(HttpStatus.BAD_REQUEST, "USER__DUPLICATE_EMAIL", "duplicate email");
    }
}
