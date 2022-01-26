package com.example.springcqrsexample.article.repository;

import com.example.springcqrsexample.article.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long>,
    ArticleCommentCustomRepository {

    void deleteByArticleId(Long articleId);
}
