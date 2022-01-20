package com.example.springcqrsexample.consumer.service;

import com.example.springcqrsexample.user.domain.RedisUser;
import com.example.springcqrsexample.user.dto.UserDto;
import com.example.springcqrsexample.user.repository.UserRedisRepository;
import com.example.springcqrsexample.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserSyncService {
    private final UserRedisRepository userRedisRepository;

    WebClient client = WebClient.create("http://localhost:8080");

    private RedisUser makeRedisUser(UserDto user) {
        return RedisUser.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .password(user.getPassword())
                .email(user.getEmail())
                .createTime(user.getCreateTime())
                .updateTime(user.getUpdateTime())
                .build();
    }

    public void syncCreate(Long userId) {
        try {
            UserDto response = client.get()
                    .uri(uriBuilder -> uriBuilder.path("/internal/users/" + userId).build())
                    .retrieve()
                    .bodyToMono(UserDto.class)
                    .block();
            RedisUser redisUser = makeRedisUser(response);
            userRedisRepository.save(redisUser);
        } catch (Exception e) {
            log.error("Fail synchronize create user");
            log.error(e.getMessage());
            return;
        }
        log.info("Synchronize create user");
    }

    public void syncUpdate(Long userId) {
        try {
            UserDto response = client.get()
                    .uri(uriBuilder -> uriBuilder.path("/internal/users/" + userId).build())
                    .retrieve()
                    .bodyToMono(UserDto.class)
                    .block();
            RedisUser redisUser = makeRedisUser(response);
            userRedisRepository.save(redisUser);
        } catch (Exception e) {
            log.error("Fail synchronize update user");
            return;
        }
        log.info("Synchronize update user");
    }

    public void syncDelete(Long userId) {
        userRedisRepository.deleteById(userId);
        log.info("Synchronize delete user");
    }
}
