package com.example.springcqrsexample.article.exception;

import com.example.springcqrsexample.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ArticleNotFoundException extends CustomException {

    public ArticleNotFoundException() {
        super(HttpStatus.BAD_REQUEST, "ARTICLE__NOT_FOUND", "article not found");
    }
}
