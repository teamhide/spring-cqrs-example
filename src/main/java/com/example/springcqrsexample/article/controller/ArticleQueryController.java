package com.example.springcqrsexample.article.controller;

import com.example.springcqrsexample.article.controller.response.GetArticleResponse;
import com.example.springcqrsexample.article.dto.ArticleDto;
import com.example.springcqrsexample.article.service.ArticleQueryService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<GetArticleResponse> getArticle(@PathVariable("articleId") Long articleId) {
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
}
