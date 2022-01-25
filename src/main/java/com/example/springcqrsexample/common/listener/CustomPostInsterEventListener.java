package com.example.springcqrsexample.common.listener;

import com.example.springcqrsexample.article.domain.Article;
import com.example.springcqrsexample.common.aws.AwsSNSClient;
import com.example.springcqrsexample.user.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomPostInsterEventListener implements PostInsertEventListener {
    private final AwsSNSClient awsSNSClient;
    private final ObjectMapper objectMapper;
    @Value("${cloud.aws.sns.arns.create-user}")
    private String createUserArn;

    @Value("${cloud.aws.sns.arns.create-article}")
    private String createArticleArn;

    @Override
    public void onPostInsert(PostInsertEvent event) {
        log.info("[*] Emit `Insert` event");
        publishRelatedEntity(event.getEntity());
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister persister) {
        return false;
    }

    private void publishRelatedEntity(Object entity) {
        try {
            publishEventForUser(entity);
            publishEventForArticle(entity);
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
    }

    private void publishEventForUser(Object entity) throws JsonProcessingException {
        if (!(entity instanceof User)) {
            return;
        }
        UserPublishRequest request = UserPublishRequest.builder()
                .userId(((User) entity).getId())
                .build();
        awsSNSClient.publish("user", createUserArn, objectMapper.writeValueAsString(request));
    }

    private void publishEventForArticle(Object entity) throws JsonProcessingException {
        if (!(entity instanceof Article)) {
            return;
        }
        ArticlePublishRequest request = ArticlePublishRequest.builder()
                .articleId(((Article) entity).getId())
                .build();
        awsSNSClient.publish("article", createArticleArn, objectMapper.writeValueAsString(request));
    }
}
