package com.example.springcqrsexample.user.controller;

import com.example.springcqrsexample.user.controller.request.RegisterUserRequest;
import com.example.springcqrsexample.user.controller.response.RegisterUserResponse;
import com.example.springcqrsexample.user.dto.RegisterUserRequestDto;
import com.example.springcqrsexample.user.dto.UserDto;
import com.example.springcqrsexample.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserV1Controller {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(@RequestBody @Valid RegisterUserRequest request) {
        RegisterUserRequestDto dto = RegisterUserRequestDto.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .password1(request.getPassword1())
                .password2(request.getPassword2())
                .build();
        UserDto user = userService.register(dto);
        RegisterUserResponse response = RegisterUserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .createTime(user.getCreateTime())
                .updateTime(user.getUpdateTime())
                .build();
        return ResponseEntity.ok(response);
    }
}
