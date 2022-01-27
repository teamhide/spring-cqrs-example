package com.example.springcqrsexample.article.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class GetCommentsRequestDto {

    private Long articleId;
    private Integer page;
    private Integer size;

    @Builder
    public GetCommentsRequestDto(Long articleId, Integer page, Integer size) {
        this.articleId = articleId;
        this.page = page;
        this.size = size;
    }
}
