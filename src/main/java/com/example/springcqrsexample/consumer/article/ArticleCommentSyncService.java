package com.example.springcqrsexample.consumer.article;

import com.example.springcqrsexample.article.domain.RedisArticleComment;
import com.example.springcqrsexample.article.dto.ArticleCommentDto;
import com.example.springcqrsexample.article.repository.ArticleCommentRedisRepository;
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
public class ArticleCommentSyncService {

    private final ArticleCommentRedisRepository articleCommentRedisRepository;
    private final String baseUrl = "http://localhost:8080";

    WebClient client = WebClient.builder()
        .baseUrl(baseUrl)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();

    private RedisArticleComment makeRedisArticleComment(ArticleCommentDto comment) {
        return RedisArticleComment.builder()
            .id(comment.getId())
            .body(comment.getBody())
            .nickname(comment.getNickname())
            .articleId(comment.getArticleId())
            .createTime(comment.getCreateTime())
            .updateTime(comment.getUpdateTime())
            .build();
    }

    public void syncCreate(Long commentId) {
        try {
            client.get()
                .uri(uriBuilder -> uriBuilder.path("/internal/articles/comments/" + commentId)
                    .build())
                .retrieve()
                .bodyToMono(ArticleCommentDto.class)
                .subscribe(response -> {
                    RedisArticleComment redisArticleComment = makeRedisArticleComment(response);
                    articleCommentRedisRepository.save(redisArticleComment);
                    log.info("Synchronize create article comment");
                });
        } catch (Exception e) {
            log.error("Fail synchronize create article comment");
            log.error(e.getMessage());
        }
    }

    public void syncUpdate(Long commentId) {
        try {
            client.get()
                .uri(uriBuilder -> uriBuilder.path("/internal/articles/comments/" + commentId)
                    .build())
                .retrieve()
                .bodyToMono(ArticleCommentDto.class)
                .subscribe(response -> {
                    RedisArticleComment redisArticleComment = makeRedisArticleComment(response);
                    articleCommentRedisRepository.save(redisArticleComment);
                    log.info("Synchronize update article comment");
                });
        } catch (Exception e) {
            log.error("Fail synchronize update article comment");
            log.error(e.getMessage());
        }
    }

    public void syncDelete(Long commentId) {
        articleCommentRedisRepository.deleteById(commentId);
        log.info("Synchronize delete article comment");
    }
}
