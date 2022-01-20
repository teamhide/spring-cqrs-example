package com.example.springcqrsexample.common.listener;

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
