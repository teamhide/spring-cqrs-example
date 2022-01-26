package com.example.springcqrsexample.common.listener.request;

import lombok.Builder;
import lombok.Data;

@Data
public class ArticleCommentPublishRequest {

    private Long articleId;

    @Builder
    public ArticleCommentPublishRequest(Long articleId) {
        this.articleId = articleId;
    }
}
