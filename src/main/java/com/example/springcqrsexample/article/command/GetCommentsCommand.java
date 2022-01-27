package com.example.springcqrsexample.article.command;

import lombok.Builder;
import lombok.Data;

@Data
public class GetCommentsCommand {

    private Long articleId;
    private Integer page;
    private Integer size;

    @Builder
    public GetCommentsCommand(Long articleId, Integer page, Integer size) {
        this.articleId = articleId;
        this.page = page;
        this.size = size;
    }
}
