package com.example.springcqrsexample.article.repository;

import com.example.springcqrsexample.article.domain.ArticleComment;
import com.example.springcqrsexample.article.dto.ArticleCommentDto;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ArticleCommentCustomRepository {

    Optional<ArticleCommentDto> getComment(Long commentId);

    Slice<ArticleComment> getCommentsByArticleIdOrderByIdAsc(Long articleId, Pageable pageable);
}
