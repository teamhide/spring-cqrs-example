package com.example.springcqrsexample.user.controller.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterUserResponse {
    private Long id;
    private String email;
    private String nickname;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @Builder
    public RegisterUserResponse(Long id, String email, String nickname, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
