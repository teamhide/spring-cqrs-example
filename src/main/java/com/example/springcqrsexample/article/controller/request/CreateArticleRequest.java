package com.example.springcqrsexample.article.controller.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateArticleRequest {
    @NotEmpty
    private String title;

    @NotEmpty
    private String description;
}
