package com.example.springcqrsexample.user.controller;

import com.example.springcqrsexample.user.controller.response.GetUserResponse;
import com.example.springcqrsexample.user.dto.UserDto;
import com.example.springcqrsexample.user.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserQueryController {
    private final UserQueryService userQueryService;

    @GetMapping("/{userId}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable("userId") Long userId) {
        UserDto user = userQueryService.getUserById(userId);
        GetUserResponse response = GetUserResponse.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .createTime(user.getCreateTime())
                .updateTime(user.getUpdateTime())
                .build();
        return ResponseEntity.ok(response);
    }
}
