package com.example.springcqrsexample.article.controller.response;

import com.example.springcqrsexample.article.dto.ArticleCommentDto;
import java.util.ArrayList;
import java.util.List;
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
    private List<ArticleCommentDto> comments = new ArrayList<>();
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @Builder
    public GetArticleResponse(Long id, String title, String description, String nickname,
        Long commentCount, List<ArticleCommentDto> comments, LocalDateTime createTime,
        LocalDateTime updateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.nickname = nickname;
        this.commentCount = commentCount;
        this.comments = comments;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
