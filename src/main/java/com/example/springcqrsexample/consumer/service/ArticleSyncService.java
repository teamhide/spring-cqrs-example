package com.example.springcqrsexample.consumer.service;

import com.example.springcqrsexample.article.domain.RedisArticle;
import com.example.springcqrsexample.article.dto.ArticleDto;
import com.example.springcqrsexample.article.repository.ArticleRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ArticleSyncService {
    private final ArticleRedisRepository articleRedisRepository;

    WebClient client = WebClient.create("http://localhost:8080");

    private RedisArticle makeRedisArticle(ArticleDto article) {
        return RedisArticle.builder()
                .id(article.getId())
                .nickname(article.getNickname())
                .title(article.getTitle())
                .description(article.getDescription())
                .commentCount(article.getCommentCount())
                .createTime(article.getCreateTime())
                .updateTime(article.getUpdateTime())
                .build();
    }

    public void syncCreate(Long articleId) {
        try {
            ArticleDto response = client.get()
                    .uri(uriBuilder -> uriBuilder.path("/internal/articles/" + articleId).build())
                    .retrieve()
                    .bodyToMono(ArticleDto.class)
                    .block();
            System.out.println("response = " + response);
            RedisArticle redisArticle = makeRedisArticle(response);
            articleRedisRepository.save(redisArticle);
        } catch (Exception e) {
            log.error("Fail synchronize create article");
            log.error(e.getMessage());
            return;
        }
        log.info("Synchronize create article");
    }
}
