package com.example.springcqrsexample.consumer.article;

import com.example.springcqrsexample.article.domain.RedisArticle;
import com.example.springcqrsexample.article.dto.ArticleDto;
import com.example.springcqrsexample.article.repository.ArticleRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ArticleSyncService {
    private final ArticleRedisRepository articleRedisRepository;
    private final String baseUrl = "http://localhost:8080";

    WebClient client = WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

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
            client.get()
                    .uri(uriBuilder -> uriBuilder.path("/internal/articles/" + articleId).build())
                    .retrieve()
                    .bodyToMono(ArticleDto.class)
                    .subscribe(response -> {
                        RedisArticle redisArticle = makeRedisArticle(response);
                        articleRedisRepository.save(redisArticle);
                        log.info("Synchronize create article");
                    });
        } catch (Exception e) {
            log.error("Fail synchronize create article");
            log.error(e.getMessage());
        }
    }

    public void syncUpdate(Long articleId) {
        try {
            client.get()
                    .uri(uriBuilder -> uriBuilder.path("/internal/articles/" + articleId).build())
                    .retrieve()
                    .bodyToMono(ArticleDto.class)
                    .subscribe(response -> {
                        RedisArticle redisArticle = makeRedisArticle(response);
                        articleRedisRepository.save(redisArticle);
                        log.info("Synchronize update article");
                    });
        } catch (Exception e) {
            log.error("Fail synchronize update article");
            log.error(e.getMessage());
        }
    }

    public void syncDelete(Long articleId) {
        articleRedisRepository.deleteById(articleId);
        log.info("Synchronize delete article");
    }
}
