package com.example.springcqrsexample.article.domain;

import java.time.LocalDateTime;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash(value = "article_comment")
public class RedisArticleComment {

    @Id
    @Indexed
    private Long id;
    private String body;
    private String nickname;
    @Indexed
    private Long articleId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @Builder
    public RedisArticleComment(Long id, String body, String nickname, Long articleId,
        LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.body = body;
        this.nickname = nickname;
        this.articleId = articleId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
