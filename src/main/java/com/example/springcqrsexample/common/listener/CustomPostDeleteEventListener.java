package com.example.springcqrsexample.common.listener;

import com.example.springcqrsexample.common.aws.AwsSNSClient;
import com.example.springcqrsexample.user.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomPostDeleteEventListener implements PostDeleteEventListener {
    private final AwsSNSClient awsSNSClient;
    private final ObjectMapper objectMapper;

    @Value("${cloud.aws.sns.arns.delete-user}")
    private String deleteUserArn;

    @Override
    public void onPostDelete(PostDeleteEvent event) {
        log.info("Emit user delete event");
        publishRelatedEntity(event.getEntity());
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister persister) {
        return false;
    }

    private void publishRelatedEntity(Object entity) {
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
        UserPublishRequest request = UserPublishRequest.builder()
                .userId(((User) entity).getId())
                .build();
        awsSNSClient.publish("user", deleteUserArn, objectMapper.writeValueAsString(request));
    }
}
