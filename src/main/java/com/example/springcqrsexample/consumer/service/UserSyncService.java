package com.example.springcqrsexample.consumer.service;

import com.example.springcqrsexample.user.domain.RedisUser;
import com.example.springcqrsexample.user.domain.User;
import com.example.springcqrsexample.user.repository.UserRedisRepository;
import com.example.springcqrsexample.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserSyncService {
    private final UserRepository userRepository;
    private final UserRedisRepository userRedisRepository;
    private final String baseUrl = "http://localhost:8000";

    private WebClient makeWebClient(String url, String contentType) {
        return WebClient.builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, contentType)
                .build();
    }

    private RedisUser makeRedisUser(User user) {
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
        WebClient webClient = makeWebClient(baseUrl, MediaType.APPLICATION_JSON_VALUE);
        WebClient.ResponseSpec retrieve = webClient.get().retrieve();
        userRepository.findById(userId).ifPresent(user -> {
            RedisUser redisUser = makeRedisUser(user);
            userRedisRepository.save(redisUser);
        });
        log.info("Synchronize create user");
    }

    public void syncUpdate(Long userId) {
        WebClient webClient = makeWebClient(baseUrl, MediaType.APPLICATION_JSON_VALUE);
        userRepository.findById(userId).ifPresent(user -> {
            RedisUser redisUser = makeRedisUser(user);
            userRedisRepository.save(redisUser);
        });
        log.info("Synchronize update user");
    }

    public void syncDelete(Long userId) {
        WebClient webClient = makeWebClient(baseUrl, MediaType.APPLICATION_JSON_VALUE);
        userRedisRepository.deleteById(userId);
        log.info("Synchronize delete user");
    }
}
