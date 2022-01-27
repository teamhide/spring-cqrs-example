package com.example.springcqrsexample.article.controller.response;

import com.example.springcqrsexample.article.dto.ArticleCommentDto;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
public class GetCommentsResponse {
    private List<ArticleCommentDto> data;

    @Builder
    public GetCommentsResponse(List<ArticleCommentDto> data) {
        this.data = data;
    }
}
