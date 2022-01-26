package com.example.springcqrsexample.consumer.user;

import com.example.springcqrsexample.user.domain.RedisUser;
import com.example.springcqrsexample.user.dto.UserDto;
import com.example.springcqrsexample.user.repository.UserRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserSyncService {

    private final UserRedisRepository userRedisRepository;
    private final String baseUrl = "http://localhost:8080";

    WebClient client = WebClient.builder()
        .baseUrl(baseUrl)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();

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
            client.get()
                .uri(uriBuilder -> uriBuilder.path("/internal/users/" + userId).build())
                .retrieve()
                .bodyToMono(UserDto.class)
                .subscribe(response -> {
                    RedisUser redisUser = makeRedisUser(response);
                    userRedisRepository.save(redisUser);
                    log.info("Synchronize create user");
                });
        } catch (Exception e) {
            log.error("Fail synchronize create user");
            log.error(e.getMessage());
        }
    }

    public void syncUpdate(Long userId) {
        try {
            client.get()
                .uri(uriBuilder -> uriBuilder.path("/internal/users/" + userId).build())
                .retrieve()
                .bodyToMono(UserDto.class)
                .subscribe(response -> {
                    RedisUser redisUser = makeRedisUser(response);
                    userRedisRepository.save(redisUser);
                    log.info("Synchronize update user");
                });
        } catch (Exception e) {
            log.error("Fail synchronize update user");
            log.error(e.getMessage());
        }
    }

    public void syncDelete(Long userId) {
        userRedisRepository.deleteById(userId);
        log.info("Synchronize delete user");
    }
}
