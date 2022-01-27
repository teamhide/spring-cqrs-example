package com.example.springcqrsexample.article.repository;

import com.example.springcqrsexample.article.domain.RedisArticleComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleCommentRedisRepository extends
    PagingAndSortingRepository<RedisArticleComment, Long> {

    Page<RedisArticleComment> findAllByArticleId(Long articleId, Pageable pageable);
}
