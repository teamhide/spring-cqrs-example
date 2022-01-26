package com.example.springcqrsexample.article.controller.request;

import lombok.Data;

@Data
public class UpdateArticleRequest {

    private String title;
    private String description;
}
