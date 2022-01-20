package com.example.springcqrsexample.user.controller;

import com.example.springcqrsexample.user.dto.UserDto;
import com.example.springcqrsexample.user.service.UserInternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/users")
@RequiredArgsConstructor
public class UserInternalController {
    private final UserInternalService userInternalService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") Long userId) {
        UserDto user = userInternalService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
}
