package com.example.springcqrsexample.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class RegisterUserRequestDto {
    private String email;
    private String nickname;
    private String password1;
    private String password2;

    @Builder
    public RegisterUserRequestDto(String email, String nickname, String password1, String password2) {
        this.email = email;
        this.nickname = nickname;
        this.password1 = password1;
        this.password2 = password2;
    }
}
