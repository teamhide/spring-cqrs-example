package com.example.springcqrsexample.article.repository;

import com.example.springcqrsexample.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleCustomRepository {
}
