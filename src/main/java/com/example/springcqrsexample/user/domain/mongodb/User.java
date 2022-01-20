package com.example.springcqrsexample.user.domain.mongodb;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Document(collation = "users")
@Getter
public class User {
    @Id
    private Long id;

    private String email;
    private String nickname;
    private String password;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @Builder
    public User(Long id, String email, String nickname, String password) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }
}
