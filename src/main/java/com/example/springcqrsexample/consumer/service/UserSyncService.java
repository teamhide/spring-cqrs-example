package com.example.springcqrsexample.consumer.service;

import com.example.springcqrsexample.user.domain.RedisUser;
import com.example.springcqrsexample.user.repository.UserRedisRepository;
import com.example.springcqrsexample.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserSyncService {
    private final UserRepository userRepository;
    private final UserRedisRepository userRedisRepository;

    public void syncById(Long userId) {
        userRepository.findById(userId).ifPresent(user -> {
            RedisUser redisUser = RedisUser.builder()
                    .id(user.getId())
                    .nickname(user.getNickname())
                    .password(user.getPassword())
                    .email(user.getEmail())
                    .createTime(user.getCreateTime())
                    .updateTime(user.getUpdateTime())
                    .build();
            userRedisRepository.save(redisUser);
        });
        log.info("Synchronize user");
    }
}
