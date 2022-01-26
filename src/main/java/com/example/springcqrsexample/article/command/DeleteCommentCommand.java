package com.example.springcqrsexample.article.command;

import lombok.Builder;
import lombok.Data;

@Data
public class DeleteCommentCommand {
    private Long userId;
    private Long articleId;
    private Long commentId;

    @Builder
    public DeleteCommentCommand(Long userId, Long articleId, Long commentId) {
        this.userId = userId;
        this.articleId = articleId;
        this.commentId = commentId;
    }
}
