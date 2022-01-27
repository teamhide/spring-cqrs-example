package com.example.springcqrsexample.article.repository;

import static com.example.springcqrsexample.article.domain.QArticle.article;
import static com.example.springcqrsexample.article.domain.QArticleComment.articleComment;
import static com.example.springcqrsexample.user.domain.QUser.user;

import com.example.springcqrsexample.article.dto.ArticleWithCommentDto;
import com.example.springcqrsexample.article.dto.QArticleWithCommentDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticleCustomRepositoryImpl implements ArticleCustomRepository {

    private final JPAQueryFactory queryFactory;

    public Optional<ArticleWithCommentDto> getArticleWithCommentInfo(Long id) {
        ArticleWithCommentDto articleDto = queryFactory.select(
                new QArticleWithCommentDto(
                    article.id,
                    article.title,
                    article.description,
                    user.nickname,
                    articleComment.count(),
                    article.createTime,
                    article.updateTime
                )
            ).from(article)
            .leftJoin(articleComment).on(articleComment.article.id.eq(article.id))
            .innerJoin(user).on(user.id.eq(article.user.id))
            .where(articleIdEq(id))
            .groupBy(article.id, user.id)
            .fetchFirst();

        return Optional.ofNullable(articleDto);
    }

    private BooleanExpression articleIdEq(Long articleId) {
        return articleId != null ? article.id.eq(articleId) : null;
    }
}
