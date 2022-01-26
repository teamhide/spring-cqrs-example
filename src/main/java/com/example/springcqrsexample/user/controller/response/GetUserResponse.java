package com.example.springcqrsexample.user.controller.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetUserResponse {

    private Long id;
    private String email;
    private String nickname;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @Builder
    public GetUserResponse(Long id, String email, String nickname, LocalDateTime createTime,
        LocalDateTime updateTime) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
