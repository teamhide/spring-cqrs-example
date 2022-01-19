package com.example.springcqrsexample.user.service;

import com.example.springcqrsexample.user.domain.User;
import com.example.springcqrsexample.user.dto.RegisterUserRequestDto;
import com.example.springcqrsexample.user.dto.UserDto;
import com.example.springcqrsexample.user.exception.DifferentPasswordException;
import com.example.springcqrsexample.user.exception.DuplicateEmailException;
import com.example.springcqrsexample.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto register(RegisterUserRequestDto dto) {
        if (!dto.getPassword1().equals(dto.getPassword2())) {
            throw new DifferentPasswordException();
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateEmailException();
        }

        User user = User.builder()
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .password(dto.getPassword1())
                .build();
        userRepository.save(user);

        return UserDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .password(user.getPassword())
                .createTime(user.getCreateTime())
                .updateTime(user.getUpdateTime())
                .build();
    }
}
