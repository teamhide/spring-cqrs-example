package com.example.springcqrsexample.user.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;


@Getter
@RedisHash(value = "users")
public class RedisUser {

    @Id
    private Long id;

    private String email;
    private String nickname;
    private String password;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @Builder
    public RedisUser(Long id, String email, String nickname, String password,
        LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
