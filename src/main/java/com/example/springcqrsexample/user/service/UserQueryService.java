package com.example.springcqrsexample.user.service;

import com.example.springcqrsexample.user.domain.RedisUser;
import com.example.springcqrsexample.user.dto.UserDto;
import com.example.springcqrsexample.user.exception.UserNotFoundException;
import com.example.springcqrsexample.user.repository.UserRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserQueryService {
    private final UserRedisRepository userRedisRepository;

    public UserDto getUserById(Long id) {
        RedisUser user = userRedisRepository.findById(id).orElseThrow(UserNotFoundException::new);
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
