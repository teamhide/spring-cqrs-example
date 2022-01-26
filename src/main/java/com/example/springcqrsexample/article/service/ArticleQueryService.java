package com.example.springcqrsexample.article.service;

import com.example.springcqrsexample.article.domain.RedisArticle;
import com.example.springcqrsexample.article.dto.ArticleDto;
import com.example.springcqrsexample.article.exception.ArticleNotFoundException;
import com.example.springcqrsexample.article.repository.ArticleRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleQueryService {

    private final ArticleRedisRepository articleRedisRepository;

    public ArticleDto getArticleById(Long articleId) {
        RedisArticle article = articleRedisRepository.findById(articleId)
            .orElseThrow(ArticleNotFoundException::new);
        return ArticleDto.builder()
            .id(article.getId())
            .title(article.getTitle())
            .description(article.getDescription())
            .nickname(article.getNickname())
            .commentCount(article.getCommentCount())
            .createTime(article.getCreateTime())
            .updateTime(article.getUpdateTime())
            .build();
    }
}
