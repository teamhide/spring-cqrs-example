package com.example.springcqrsexample.article.command;

import lombok.Builder;
import lombok.Data;

@Data
public class DeleteArticleCommand {
    private Long userId;
    private Long articleId;

    @Builder
    public DeleteArticleCommand(Long userId, Long articleId) {
        this.userId = userId;
        this.articleId = articleId;
    }
}
