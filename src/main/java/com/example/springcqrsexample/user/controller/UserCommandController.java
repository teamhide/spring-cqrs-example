package com.example.springcqrsexample.user.controller;

import com.example.springcqrsexample.user.command.RegisterUserCommand;
import com.example.springcqrsexample.user.controller.request.RegisterUserRequest;
import com.example.springcqrsexample.user.service.UserCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
