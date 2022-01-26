package com.example.springcqrsexample.article.command;

import lombok.Builder;
import lombok.Data;

@Data
public class CreateArticleCommentCommand {

    private Long userId;
    private Long articleId;
    private String body;

    @Builder
    public CreateArticleCommentCommand(Long userId, Long articleId, String body) {
        this.userId = userId;
        this.articleId = articleId;
        this.body = body;
    }
}
