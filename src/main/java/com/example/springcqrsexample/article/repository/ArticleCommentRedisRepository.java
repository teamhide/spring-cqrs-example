package com.example.springcqrsexample.article.repository;

import com.example.springcqrsexample.article.domain.RedisArticleComment;
import org.springframework.data.repository.CrudRepository;

public interface ArticleCommentRedisRepository extends CrudRepository<RedisArticleComment, Long> {

}
