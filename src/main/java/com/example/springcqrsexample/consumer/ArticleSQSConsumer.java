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
public class ArticleSQSConsumer implements SQSConsumer<ArticleSQSMessage> {
    private final ArticleSyncService articleSyncService;

    @SqsListener(value = "create-article", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    @Override
    public void consumeCreate(@NotificationMessage ArticleSQSMessage message, Acknowledgment ack) {
        log.info("Received `Create Article` event");
        articleSyncService.syncCreate(message.getArticleId());
        ack.acknowledge();
    }

    @SqsListener(value = "update-article", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    @Override
    public void consumeUpdate(@NotificationMessage  ArticleSQSMessage message, Acknowledgment ack) {
        log.info("Received `Update Article` event");
    }

    @SqsListener(value = "delete-article", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    @Override
    public void consumeDelete(@NotificationMessage  ArticleSQSMessage message, Acknowledgment ack) {
        log.info("Received `Delete Article` event");
    }
}
