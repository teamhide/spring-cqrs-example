package com.example.springcqrsexample.article.controller;

import com.example.springcqrsexample.article.dto.ArticleCommentDto;
import com.example.springcqrsexample.article.dto.ArticleDto;
import com.example.springcqrsexample.article.service.ArticleInternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/articles")
@RequiredArgsConstructor
public class ArticleInternalController {

    private final ArticleInternalService articleInternalService;

    @GetMapping("/{articleId}")
    public ArticleDto getArticle(@PathVariable("articleId") Long articleId) {
        return articleInternalService.getArticle(articleId);
    }

    @GetMapping("/comments/{commentId}")
    public ArticleCommentDto getComment(@PathVariable("commentId") Long commentId) {
        return articleInternalService.getArticleComment(commentId);
    }
}
