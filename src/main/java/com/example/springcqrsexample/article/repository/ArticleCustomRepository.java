package com.example.springcqrsexample.article.repository;

import com.example.springcqrsexample.article.dto.ArticleDto;

import java.util.Optional;

public interface ArticleCustomRepository {
    Optional<ArticleDto> getArticleWithCommentInfo(Long id);
}
