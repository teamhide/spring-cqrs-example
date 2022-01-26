package com.example.springcqrsexample.article.repository;

import static com.example.springcqrsexample.article.domain.QArticleComment.articleComment;
import static com.example.springcqrsexample.user.domain.QUser.user;

import com.example.springcqrsexample.article.dto.ArticleCommentDto;
import com.example.springcqrsexample.article.dto.QArticleCommentDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticleCommentCustomRepositoryImpl implements ArticleCommentCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<ArticleCommentDto> getComment(Long commentId) {
        ArticleCommentDto commentDto = queryFactory.select(
                new QArticleCommentDto(articleComment.id, articleComment.body, user.nickname,
                    articleComment.article.id, articleComment.createTime, articleComment.updateTime))
            .from(articleComment)
            .join(user).on(user.id.eq(articleComment.user.id))
            .where(commentIdEq(commentId))
            .fetchFirst();

        return Optional.ofNullable(commentDto);
    }

    private BooleanExpression commentIdEq(Long commentId) {
        return commentId != null ? articleComment.id.eq(commentId) : null;
    }
}
