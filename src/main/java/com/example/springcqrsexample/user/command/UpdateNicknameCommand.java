package com.example.springcqrsexample.user.command;

import lombok.Builder;
import lombok.Data;

@Data
public class UpdateNicknameCommand {
    private Long userId;
    private String nickname;

    @Builder
    public UpdateNicknameCommand(Long userId, String nickname) {
        this.userId = userId;
        this.nickname = nickname;
    }
}
