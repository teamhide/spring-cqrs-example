package com.example.springcqrsexample.article.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@RedisHash(value = "article")
public class RedisArticle {
    @Id
    private Long id;
    private String title;
    private String description;
    private String nickname;
    private Long commentCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @Builder
    public RedisArticle(Long id, String title, String description, String nickname, Long commentCount, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.nickname = nickname;
        this.commentCount = commentCount;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
