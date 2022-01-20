package com.example.springcqrsexample.user.command;

import lombok.Builder;
import lombok.Data;

@Data
public class DeleteUserCommand {
    private Long userId;

    @Builder
    public DeleteUserCommand(Long userId) {
        this.userId = userId;
    }
}
