package com.example.springcqrsexample.article.controller.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetArticleResponse {

    private Long id;
    private String title;
    private String description;
    private String nickname;
    private Long commentCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @Builder
    public GetArticleResponse(Long id, String title, String description, String nickname,
        Long commentCount, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.nickname = nickname;
        this.commentCount = commentCount;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
