package com.example.springcqrsexample.article.controller;

import com.example.springcqrsexample.article.dto.ArticleCommentDto;
import com.example.springcqrsexample.article.dto.ArticleWithCommentDto;
import com.example.springcqrsexample.article.dto.GetCommentsRequestDto;
import com.example.springcqrsexample.article.dto.GetCommentsResponseDto;
import com.example.springcqrsexample.article.service.ArticleInternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ArticleWithCommentDto> getArticle(@PathVariable("articleId") Long articleId) {
        ArticleWithCommentDto article = articleInternalService.getArticle(articleId);
        return ResponseEntity.ok(article);
    }

    @GetMapping("{articleId}/comments")
    public ResponseEntity<GetCommentsResponseDto> getComments(
        @PathVariable("articleId") Long articleId, @PageableDefault(page = 0, size = 10)
        Pageable pageable) {
        GetCommentsRequestDto requestDto = GetCommentsRequestDto.builder()
            .articleId(articleId)
            .page(pageable.getPageNumber())
            .size(pageable.getPageSize())
            .build();
        GetCommentsResponseDto response = articleInternalService.getComments(requestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/comments/{commentId}")
    public ArticleCommentDto getComment(@PathVariable("commentId") Long commentId) {
        return articleInternalService.getArticleComment(commentId);
    }
}
