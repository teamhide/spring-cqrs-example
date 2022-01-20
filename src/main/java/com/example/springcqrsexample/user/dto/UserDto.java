package com.example.springcqrsexample.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {
    private Long id;
    private String email;
    private String nickname;
    private String password;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @Builder
    public UserDto(Long id, String email, String nickname, String password, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
