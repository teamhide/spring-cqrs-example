package com.example.springcqrsexample.article.repository;

import com.example.springcqrsexample.article.dto.ArticleWithCommentDto;
import java.util.Optional;

public interface ArticleCustomRepository {

    Optional<ArticleWithCommentDto> getArticleWithCommentInfo(Long id);
}
