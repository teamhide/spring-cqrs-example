package com.example.springcqrsexample.article.command;

import lombok.Builder;
import lombok.Data;

@Data
public class CreateArticleCommand {

    private Long userId;
    private String title;
    private String description;

    @Builder
    public CreateArticleCommand(Long userId, String title, String description) {
        this.userId = userId;
        this.title = title;
        this.description = description;
    }
}
