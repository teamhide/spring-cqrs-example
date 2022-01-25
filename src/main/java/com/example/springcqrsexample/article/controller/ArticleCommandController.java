package com.example.springcqrsexample.article.controller;

import com.example.springcqrsexample.article.command.CreateArticleCommand;
import com.example.springcqrsexample.article.command.CreateArticleCommentCommand;
import com.example.springcqrsexample.article.command.DeleteArticleCommand;
import com.example.springcqrsexample.article.command.UpdateArticleCommand;
import com.example.springcqrsexample.article.controller.request.CreateArticleCommentRequest;
import com.example.springcqrsexample.article.controller.request.CreateArticleRequest;
import com.example.springcqrsexample.article.controller.request.UpdateArticleRequest;
import com.example.springcqrsexample.article.service.ArticleCommandService;
import com.example.springcqrsexample.common.interceptor.Permission;
import com.example.springcqrsexample.common.interceptor.PermissionRole;
import com.example.springcqrsexample.common.resolver.CurrentUser;
import com.example.springcqrsexample.common.resolver.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleCommandController {
    private final ArticleCommandService articleCommandService;

    @Permission(role = PermissionRole.MEMBER)
    @PostMapping("")
    public ResponseEntity<Object> createArticle(@RequestBody @Valid CreateArticleRequest request, @CurrentUser UserInfo user) {
        CreateArticleCommand command = CreateArticleCommand.builder()
                .userId(user.getId())
                .title(request.getTitle())
                .description(request.getDescription())
                .build();
        articleCommandService.createArticle(command);
        return ResponseEntity.ok().build();
    }

    @Permission(role = PermissionRole.MEMBER)
    @PutMapping("/{articleId}")
    public ResponseEntity<Object> updateArticle(
            @RequestBody @Valid UpdateArticleRequest request,
            @PathVariable("articleId") Long articleId,
            @CurrentUser UserInfo user
    ) {
        UpdateArticleCommand command = UpdateArticleCommand.builder()
                .userId(user.getId())
                .articleId(articleId)
                .title(request.getTitle())
                .description(request.getDescription())
                .build();
        articleCommandService.updateArticle(command);
        return ResponseEntity.ok().build();
    }

    @Permission(role = PermissionRole.MEMBER)
    @DeleteMapping("/{articleId}")
    public ResponseEntity<Object> deleteArticle(@PathVariable("articleId") Long articleId, @CurrentUser UserInfo user) {
        DeleteArticleCommand command = DeleteArticleCommand.builder()
                .userId(user.getId())
                .articleId(articleId)
                .build();
        articleCommandService.deleteArticle(command);
        return ResponseEntity.ok().build();
    }

    @Permission(role = PermissionRole.MEMBER)
    @PostMapping("/{articleId}/comments")
    public ResponseEntity<Object> createComment(
            @RequestBody @Valid CreateArticleCommentRequest request,
            @PathVariable("articleId") Long articleId,
            @CurrentUser UserInfo user
    ) {
        CreateArticleCommentCommand command = CreateArticleCommentCommand.builder()
                .userId(user.getId())
                .articleId(articleId)
                .body(request.getBody())
                .build();
        articleCommandService.createComment(command);
        return ResponseEntity.ok().build();
    }
}
