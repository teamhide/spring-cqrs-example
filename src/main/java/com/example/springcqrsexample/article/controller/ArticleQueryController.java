package com.example.springcqrsexample.article.controller;

import com.example.springcqrsexample.article.command.GetCommentsCommand;
import com.example.springcqrsexample.article.controller.response.GetArticleResponse;
import com.example.springcqrsexample.article.controller.response.GetCommentsResponse;
import com.example.springcqrsexample.article.dto.ArticleCommentDto;
import com.example.springcqrsexample.article.dto.ArticleDto;
import com.example.springcqrsexample.article.service.ArticleQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleQueryController {

    private final ArticleQueryService articleQueryService;

    @GetMapping("/{articleId}")
    public ResponseEntity<GetArticleResponse> getArticle(
        @PathVariable("articleId") Long articleId) {
        ArticleDto article = articleQueryService.getArticleById(articleId);
        GetArticleResponse response = GetArticleResponse.builder()
            .id(article.getId())
            .title(article.getTitle())
            .description(article.getDescription())
            .nickname(article.getNickname())
            .commentCount(article.getCommentCount())
            .createTime(article.getCreateTime())
            .updateTime(article.getUpdateTime())
            .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{articleId}/comments")
    public ResponseEntity<GetCommentsResponse> getComments(
        @PathVariable("articleId") Long articleId,
        @PageableDefault(page = 0, size = 10) Pageable pageable) {
        GetCommentsCommand command = GetCommentsCommand.builder()
            .articleId(articleId)
            .page(pageable.getPageNumber())
            .size(pageable.getPageSize())
            .build();
        List<ArticleCommentDto> comments = articleQueryService.getComments(command);
        GetCommentsResponse response = GetCommentsResponse.builder()
            .data(comments)
            .build();
        return ResponseEntity.ok(response);
    }
}
