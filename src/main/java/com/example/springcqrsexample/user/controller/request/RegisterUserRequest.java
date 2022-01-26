package com.example.springcqrsexample.user.controller.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegisterUserRequest {

    @NotEmpty
    private String email;

    @NotEmpty
    private String nickname;

    @NotEmpty
    private String password1;

    @NotEmpty
    private String password2;
}
