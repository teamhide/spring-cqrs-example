package com.example.springcqrsexample.article.exception;

import com.example.springcqrsexample.common.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ArticleCommentPermissionException extends CustomException {
    public ArticleCommentPermissionException() {
        super(HttpStatus.FORBIDDEN, "ARTICLE_COMMENT__PERMISSION", "article comment do not have permission");
    }
}
