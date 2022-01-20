package com.example.springcqrsexample.user.controller.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UpdateNicknameRequest {
    @NotEmpty
    private String nickname;
}
