package com.example.springcqrsexample.user.service;

import com.example.springcqrsexample.user.domain.User;
import com.example.springcqrsexample.user.dto.UserDto;
import com.example.springcqrsexample.user.exception.UserNotFoundException;
import com.example.springcqrsexample.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInternalService {

    private final UserRepository userRepository;

    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return UserDto.builder()
            .id(user.getId())
            .email(user.getEmail())
            .nickname(user.getNickname())
            .password(user.getPassword())
            .createTime(user.getCreateTime())
            .updateTime(user.getUpdateTime())
            .build();
    }
}
