package com.example.springcqrsexample.article.exception;

import com.example.springcqrsexample.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ArticleCommentNotFoundException extends CustomException {
    public ArticleCommentNotFoundException() {
        super(HttpStatus.NOT_FOUND, "ARTICLE_COMMENT__NOT_FOUND", "article comment not found");
    }
}
