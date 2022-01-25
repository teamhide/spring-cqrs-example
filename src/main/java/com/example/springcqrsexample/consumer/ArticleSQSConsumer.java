package com.example.springcqrsexample.consumer;

import com.example.springcqrsexample.consumer.service.ArticleSyncService;
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
public class ArticleSQSConsumer {
    private final ArticleSyncService articleSyncService;

    @SqsListener(value = "create-article", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void consumeCreate(@NotificationMessage ArticleSQSMessage message, Acknowledgment ack) {
        log.info("Received `Create Article` event");
        articleSyncService.syncCreate(message.getArticleId());
        ack.acknowledge();
    }
}
