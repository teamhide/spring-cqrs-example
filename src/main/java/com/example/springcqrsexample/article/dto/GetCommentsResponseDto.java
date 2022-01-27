package com.example.springcqrsexample.article.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
public class GetCommentsResponseDto {
    private List<ArticleCommentDto> comments;
    private boolean last;

    @Builder
    public GetCommentsResponseDto(List<ArticleCommentDto> comments, boolean last) {
        this.comments = comments;
        this.last = last;
    }
}
