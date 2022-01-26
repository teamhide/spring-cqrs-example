package com.example.springcqrsexample.common.listener.request;

import lombok.Builder;
import lombok.Data;

@Data
public class ArticlePublishRequest {

    private Long articleId;

    @Builder
    public ArticlePublishRequest(Long articleId) {
        this.articleId = articleId;
    }
}
