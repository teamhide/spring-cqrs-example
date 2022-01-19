package com.example.springcqrsexample.common.aws;

import lombok.Builder;
import lombok.Data;

@Data
public class PublishRequest {
    private String topicArn;
    private String message;

    @Builder
    public PublishRequest(String topicArn, String message) {
        this.topicArn = topicArn;
        this.message = message;
    }
}
