package com.example.springcqrsexample.article.domain;

import com.example.springcqrsexample.common.BaseTimestampEntity;
import com.example.springcqrsexample.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "article_comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleComment extends BaseTimestampEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "body")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    public void setArticle(Article article) {
        this.article = article;
    }

    public void changeBody(String body) {
        this.body = body;
    }

    @Builder
    public ArticleComment(Long id, String body, User user, Article article) {
        this.id = id;
        this.body = body;
        this.user = user;
        this.article = article;
    }
}
