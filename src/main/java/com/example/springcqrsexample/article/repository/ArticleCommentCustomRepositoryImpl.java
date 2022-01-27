package com.example.springcqrsexample.article.repository;

import static com.example.springcqrsexample.article.domain.QArticleComment.articleComment;
import static com.example.springcqrsexample.user.domain.QUser.user;

import com.example.springcqrsexample.article.domain.ArticleComment;
import com.example.springcqrsexample.article.dto.ArticleCommentDto;
import com.example.springcqrsexample.article.dto.QArticleCommentDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

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

    @Override
    public Slice<ArticleComment> getCommentsByArticleIdOrderByIdAsc(Long articleId, Pageable pageable) {
        JPAQuery<ArticleComment> query = queryFactory.selectFrom(articleComment)
            .innerJoin(articleComment.user).fetchJoin()
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize() + 1)
            .orderBy(articleComment.id.asc());

        return makeSlice(query.fetch(), pageable);
    }

    private <T> Slice<T> makeSlice(List<T> content, Pageable pageable) {
        boolean hasNext = false;
        if (content.size() > pageable.getPageSize()) {
            content.remove(pageable.getPageSize());
            hasNext = true;
        }
        return new SliceImpl<>(content, pageable, hasNext);
    }

    private BooleanExpression commentIdEq(Long commentId) {
        return commentId != null ? articleComment.id.eq(commentId) : null;
    }
}
