package com.example.springcqrsexample.article.service;

import com.example.springcqrsexample.article.dto.ArticleCommentDto;
import com.example.springcqrsexample.article.dto.ArticleDto;
import com.example.springcqrsexample.article.exception.ArticleCommentNotFoundException;
import com.example.springcqrsexample.article.exception.ArticleNotFoundException;
import com.example.springcqrsexample.article.repository.ArticleCommentRepository;
import com.example.springcqrsexample.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleInternalService {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public ArticleDto getArticle(Long articleId) {
        return articleRepository.getArticleWithCommentInfo(articleId)
            .orElseThrow(ArticleNotFoundException::new);
    }

    public ArticleCommentDto getArticleComment(Long commentId) {
        return articleCommentRepository.getComment(commentId).orElseThrow(
            ArticleCommentNotFoundException::new);
    }
}
