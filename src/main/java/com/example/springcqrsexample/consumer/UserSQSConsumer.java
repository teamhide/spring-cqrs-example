package com.example.springcqrsexample.consumer;

import com.example.springcqrsexample.consumer.service.UserSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.config.annotation.NotificationMessage;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserSQSConsumer {
    private final UserSyncService userSyncService;

    @SqsListener(value = "create-user", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void consumeCreate(@NotificationMessage UserSQSMessage message, Acknowledgment ack) {
        log.info("Received `Create User` event");
        userSyncService.syncById(message.getUserId());
        ack.acknowledge();
    }

    @SqsListener(value = "update-user", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void consumeUpdate(@NotificationMessage UserSQSMessage message, Acknowledgment ack) {
        log.info("Received `Update User` event");
        userSyncService.syncById(message.getUserId());
        ack.acknowledge();
    }
}
