package com.example.springcqrsexample.article.domain;

import com.example.springcqrsexample.common.BaseTimestampEntity;
import com.example.springcqrsexample.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "article")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends BaseTimestampEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 30)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<ArticleComment> articleComments = new ArrayList<>();

    public void addComment(ArticleComment articleComment) {
        articleComment.setArticle(this);
        articleComments.add(articleComment);
    }

    @Builder
    public Article(Long id, String title, String description, User user, ArticleComment... articleComments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user = user;
        if (articleComments != null) {
            for (ArticleComment articleComment : articleComments) {
                this.addComment(articleComment);
            }
        }
    }
}
