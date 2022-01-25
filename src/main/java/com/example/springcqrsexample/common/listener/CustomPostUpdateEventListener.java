package com.example.springcqrsexample.common.listener;

import com.example.springcqrsexample.article.domain.Article;
import com.example.springcqrsexample.common.aws.AwsSNSClient;
import com.example.springcqrsexample.user.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomPostUpdateEventListener implements PostUpdateEventListener {
    private final AwsSNSClient awsSNSClient;
    private final ObjectMapper objectMapper;

    @Value("${cloud.aws.sns.arns.update-user}")
    private String updateUserArn;

    @Value("${cloud.aws.sns.arns.update-article}")
    private String updateArticleArn;

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        log.info("Emit `Update` event");
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
        awsSNSClient.publish("user", updateUserArn, objectMapper.writeValueAsString(request));
    }

    private void publishEventForArticle(Object entity) throws JsonProcessingException {
        if (!(entity instanceof Article)) {
            return;
        }
        ArticlePublishRequest request = ArticlePublishRequest.builder()
                .articleId(((Article) entity).getId())
                .build();
        awsSNSClient.publish("article", updateArticleArn, objectMapper.writeValueAsString(request));
    }
}
