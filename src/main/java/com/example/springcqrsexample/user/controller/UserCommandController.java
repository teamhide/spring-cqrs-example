package com.example.springcqrsexample.user.controller;

import com.example.springcqrsexample.user.command.DeleteUserCommand;
import com.example.springcqrsexample.user.command.RegisterUserCommand;
import com.example.springcqrsexample.user.command.UpdateNicknameCommand;
import com.example.springcqrsexample.user.controller.request.RegisterUserRequest;
import com.example.springcqrsexample.user.controller.request.UpdateNicknameRequest;
import com.example.springcqrsexample.user.service.UserCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserCommandController {

    private final UserCommandService userCommandService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterUserRequest request) {
        RegisterUserCommand command = RegisterUserCommand.builder()
            .email(request.getEmail())
            .nickname(request.getNickname())
            .password1(request.getPassword1())
            .password2(request.getPassword2())
            .build();
        userCommandService.register(command);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateNickname(
        @PathVariable("userId") Long userId,
        @RequestBody @Valid UpdateNicknameRequest request
    ) {
        UpdateNicknameCommand command = UpdateNicknameCommand.builder()
            .userId(userId)
            .nickname(request.getNickname())
            .build();
        userCommandService.updateNickname(command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> delete(@PathVariable("userId") Long userId) {
        DeleteUserCommand command = DeleteUserCommand.builder()
            .userId(userId)
            .build();
        userCommandService.delete(command);
        return ResponseEntity.ok().build();
    }
}
