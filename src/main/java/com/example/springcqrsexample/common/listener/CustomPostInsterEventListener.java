package com.example.springcqrsexample.common.listener;

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

import java.util.HashMap;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomPostInsterEventListener implements PostInsertEventListener {
    private final AwsSNSClient awsSNSClient;
    private final ObjectMapper objectMapper;
    @Value("${cloud.aws.sns.arns.create-article}")
    private String createArticleArn;

    @Override
    public void onPostInsert(PostInsertEvent event) {
        publishReleatedEntity(event.getEntity());
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister persister) {
        return false;
    }

    private void publishReleatedEntity(Object entity) {
        try {
            publishEventForUser(entity);
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
    }

    private void publishEventForUser(Object entity) throws JsonProcessingException {
        if (!(entity instanceof User)) {
            return;
        }
        HashMap<String, Long> message = new HashMap<>();
        message.put("id", ((User) entity).getId());
        awsSNSClient.publish(createArticleArn, objectMapper.writeValueAsString(message));
    }
}
