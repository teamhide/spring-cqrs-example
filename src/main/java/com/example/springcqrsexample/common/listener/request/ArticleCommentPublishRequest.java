package com.example.springcqrsexample.common.listener.request;

import lombok.Builder;
import lombok.Data;

@Data
public class ArticleCommentPublishRequest {

    private Long articleId;
    private Long commentId;

    @Builder
    public ArticleCommentPublishRequest(Long articleId, Long commentId) {
        this.articleId = articleId;
        this.commentId = commentId;
    }
}
