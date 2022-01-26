package com.example.springcqrsexample.article.command;

import lombok.Builder;
import lombok.Data;

@Data
public class UpdateArticleCommand {

    private Long userId;
    private Long articleId;

    private String title;
    private String description;

    @Builder
    public UpdateArticleCommand(Long userId, Long articleId, String title, String description) {
        this.userId = userId;
        this.articleId = articleId;
        this.title = title;
        this.description = description;
    }
}
