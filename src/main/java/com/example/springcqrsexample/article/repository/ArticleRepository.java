package com.example.springcqrsexample.article.repository;

import com.example.springcqrsexample.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleCustomRepository {
    Optional<Article> findByIdAndUserId(Long id, Long userId);
}
