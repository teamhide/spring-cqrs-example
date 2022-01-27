package com.example.springcqrsexample.article.service;

import com.example.springcqrsexample.article.command.GetCommentsCommand;
import com.example.springcqrsexample.article.domain.RedisArticle;
import com.example.springcqrsexample.article.domain.RedisArticleComment;
import com.example.springcqrsexample.article.dto.ArticleCommentDto;
import com.example.springcqrsexample.article.dto.ArticleDto;
import com.example.springcqrsexample.article.exception.ArticleNotFoundException;
import com.example.springcqrsexample.article.repository.ArticleCommentRedisRepository;
import com.example.springcqrsexample.article.repository.ArticleRedisRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleQueryService {

    private final ArticleRedisRepository articleRedisRepository;
    private final ArticleCommentRedisRepository articleCommentRedisRepository;

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

    public List<ArticleCommentDto> getComments(GetCommentsCommand command) {
        PageRequest pageRequest = PageRequest.of(command.getPage(), command.getSize());
        Page<RedisArticleComment> result = articleCommentRedisRepository.findAll(pageRequest);
        List<RedisArticleComment> contents = result.getContent();

        return contents.stream().map(comment ->
            ArticleCommentDto.builder()
                .id(comment.getId())
                .body(comment.getBody())
                .nickname(comment.getNickname())
                .articleId(comment.getArticleId())
                .createTime(comment.getCreateTime())
                .updateTime(comment.getUpdateTime())
                .build()
        ).collect(Collectors.toList());
    }
}
