package com.example.springcqrsexample.common.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CustomExceptionResponse {
    private String errorCode;
    private String detail;

    @Builder
    public CustomExceptionResponse(String errorCode, String detail) {
        this.errorCode = errorCode;
        this.detail = detail;
    }
}
