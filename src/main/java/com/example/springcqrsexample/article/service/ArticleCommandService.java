package com.example.springcqrsexample.article.service;

import com.example.springcqrsexample.article.command.CreateArticleCommand;
import com.example.springcqrsexample.article.command.CreateArticleCommentCommand;
import com.example.springcqrsexample.article.domain.Article;
import com.example.springcqrsexample.article.domain.ArticleComment;
import com.example.springcqrsexample.article.exception.ArticleNotFoundException;
import com.example.springcqrsexample.article.repository.ArticleCommentRepository;
import com.example.springcqrsexample.article.repository.ArticleRepository;
import com.example.springcqrsexample.user.domain.User;
import com.example.springcqrsexample.user.exception.UserNotFoundException;
import com.example.springcqrsexample.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommandService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public void createArticle(CreateArticleCommand command) {
        System.out.println(command);
        User user = userRepository.findById(command.getUserId()).orElseThrow(UserNotFoundException::new);
        Article article = Article.builder()
                .title(command.getTitle())
                .description(command.getDescription())
                .user(user)
                .build();
        articleRepository.save(article);
    }

    public void createComment(CreateArticleCommentCommand command) {
        User user = userRepository.findById(command.getUserId()).orElseThrow(UserNotFoundException::new);
        Article article = articleRepository.findById(command.getArticleId()).orElseThrow(ArticleNotFoundException::new);
        ArticleComment comment = ArticleComment.builder()
                .body(command.getBody())
                .user(user)
                .article(article)
                .build();
        articleCommentRepository.save(comment);
    }
}
