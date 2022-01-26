package com.example.springcqrsexample.article.repository;

import com.example.springcqrsexample.article.dto.ArticleCommentDto;
import java.util.Optional;

public interface ArticleCommentCustomRepository {

    Optional<ArticleCommentDto> getComment(Long commentId);
}
