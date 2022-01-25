package com.example.springcqrsexample.article.repository;

import com.example.springcqrsexample.article.domain.RedisArticle;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRedisRepository extends CrudRepository<RedisArticle, Long> {
}
