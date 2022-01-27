package com.example.springcqrsexample.article.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleWithCommentDto {

    private Long id;
    private String title;
    private String description;
    private String nickname;
    private Long commentCount;
    private List<ArticleCommentDto> comments;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @QueryProjection
    @Builder
    public ArticleWithCommentDto(Long id, String title, String description, String nickname,
        Long commentCount, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.nickname = nickname;
        this.commentCount = commentCount;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public void setComments(List<ArticleCommentDto> comments) {
        this.comments = comments;
    }
}
