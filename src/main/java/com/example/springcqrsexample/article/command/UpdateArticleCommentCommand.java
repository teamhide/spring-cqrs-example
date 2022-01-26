package com.example.springcqrsexample.article.command;

import lombok.Builder;
import lombok.Data;

@Data
public class UpdateArticleCommentCommand {

    private Long articleId;
    private Long commentId;
    private Long userId;
    private String body;

    @Builder
    public UpdateArticleCommentCommand(Long articleId, Long commentId, Long userId, String body) {
        this.articleId = articleId;
        this.commentId = commentId;
        this.userId = userId;
        this.body = body;
    }
}
