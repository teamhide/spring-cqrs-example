package com.example.springcqrsexample.article.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleDto {
    private Long id;
    private String title;
    private String description;
    private String nickname;
    private Long commentCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @QueryProjection
    public ArticleDto(Long id, String title, String description, String nickname, Long commentCount, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.nickname = nickname;
        this.commentCount = commentCount;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
