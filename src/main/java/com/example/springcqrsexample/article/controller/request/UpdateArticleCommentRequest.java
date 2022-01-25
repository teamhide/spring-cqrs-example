package com.example.springcqrsexample.article.controller.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UpdateArticleCommentRequest {
    @NotEmpty
    private String body;
}
