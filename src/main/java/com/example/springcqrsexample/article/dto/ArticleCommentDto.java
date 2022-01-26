package com.example.springcqrsexample.article.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleCommentDto {

    private Long id;
    private String body;
    private String nickname;
    private Long articleId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @QueryProjection
    @Builder
    public ArticleCommentDto(Long id, String body, String nickname, Long articleId,
        LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.body = body;
        this.nickname = nickname;
        this.articleId = articleId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
