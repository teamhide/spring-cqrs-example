package com.example.springcqrsexample.consumer.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleSQSMessage {

    private Long articleId;
}
