package com.example.springcqrsexample.common.listener.request;

import lombok.Builder;
import lombok.Data;

@Data
public class UserPublishRequest {
    private Long userId;

    @Builder
    public UserPublishRequest(Long userId) {
        this.userId = userId;
    }
}
