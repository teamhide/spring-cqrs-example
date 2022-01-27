package com.example.springcqrsexample.article.service;

import com.example.springcqrsexample.article.domain.ArticleComment;
import com.example.springcqrsexample.article.dto.ArticleCommentDto;
import com.example.springcqrsexample.article.dto.ArticleWithCommentDto;
import com.example.springcqrsexample.article.dto.GetCommentsRequestDto;
import com.example.springcqrsexample.article.dto.GetCommentsResponseDto;
import com.example.springcqrsexample.article.exception.ArticleCommentNotFoundException;
import com.example.springcqrsexample.article.exception.ArticleNotFoundException;
import com.example.springcqrsexample.article.repository.ArticleCommentRepository;
import com.example.springcqrsexample.article.repository.ArticleRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleInternalService {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public ArticleWithCommentDto getArticle(Long articleId) {
        ArticleWithCommentDto article = articleRepository.getArticleWithCommentInfo(articleId)
            .orElseThrow(ArticleNotFoundException::new);

        PageRequest pageRequest = PageRequest.of(0, 10);
        List<ArticleCommentDto> comments = articleCommentRepository
            .getCommentsByArticleIdOrderByIdAsc(articleId, pageRequest)
            .getContent()
            .stream()
            .map(comment -> ArticleCommentDto.builder()
                .id(comment.getId())
                .body(comment.getBody())
                .nickname(comment.getUser().getNickname())
                .articleId(comment.getArticle().getId())
                .createTime(comment.getCreateTime())
                .updateTime(comment.getUpdateTime())
                .build()
            ).collect(Collectors.toList());

        article.setComments(comments);
        return article;
    }

    public ArticleCommentDto getArticleComment(Long commentId) {
        return articleCommentRepository.getComment(commentId).orElseThrow(
            ArticleCommentNotFoundException::new);
    }

    public GetCommentsResponseDto getComments(GetCommentsRequestDto requestDto) {
        PageRequest pageRequest = PageRequest.of(requestDto.getPage(), requestDto.getSize());
        Slice<ArticleComment> result = articleCommentRepository.getCommentsByArticleIdOrderByIdAsc(
            requestDto.getArticleId(), pageRequest);
        List<ArticleComment> content = result.getContent();

        List<ArticleCommentDto> comments = content.stream()
            .map(comment -> ArticleCommentDto.builder()
                .id(comment.getId())
                .body(comment.getBody())
                .nickname(comment.getUser().getNickname())
                .articleId(comment.getArticle().getId())
                .createTime(comment.getCreateTime())
                .updateTime(comment.getUpdateTime())
                .build()
            ).collect(Collectors.toList());

        return GetCommentsResponseDto.builder()
            .comments(comments)
            .last(result.isLast())
            .build();
    }
}
