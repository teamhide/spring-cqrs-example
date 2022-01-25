package com.example.springcqrsexample.consumer.article;

import com.example.springcqrsexample.consumer.SQSConsumer;
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
public class ArticleCommentSQSConsumer implements SQSConsumer<ArticleCommentSQSMessage> {
    private final ArticleSyncService articleSyncService;

    @SqsListener(value = "${cloud.aws.sqs.queue.create-article-comment}", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    @Override
    public void consumeCreate(@NotificationMessage ArticleCommentSQSMessage message, Acknowledgment ack) {
        log.info("Received `Create ArticleComment` event");
        articleSyncService.syncCreate(message.getArticleId());
        ack.acknowledge();
    }

    @SqsListener(value = "${cloud.aws.sqs.queue.update-article-comment}", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    @Override
    public void consumeUpdate(@NotificationMessage ArticleCommentSQSMessage message, Acknowledgment ack) {
        log.info("Received `Update ArticleComment` event");
        articleSyncService.syncUpdate(message.getArticleId());
        ack.acknowledge();
    }

    @SqsListener(value = "${cloud.aws.sqs.queue.delete-article-comment}", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    @Override
    public void consumeDelete(@NotificationMessage ArticleCommentSQSMessage message, Acknowledgment ack) {
        log.info("Received `Delete ArticleComment` event");
        articleSyncService.syncDelete(message.getArticleId());
        ack.acknowledge();
    }
}
