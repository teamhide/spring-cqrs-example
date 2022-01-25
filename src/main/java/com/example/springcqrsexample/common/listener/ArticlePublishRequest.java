package com.example.springcqrsexample.common.listener;

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
