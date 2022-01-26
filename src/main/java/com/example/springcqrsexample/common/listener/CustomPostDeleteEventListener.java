package com.example.springcqrsexample.common.listener;

import com.example.springcqrsexample.article.domain.Article;
import com.example.springcqrsexample.article.domain.ArticleComment;
import com.example.springcqrsexample.common.aws.AwsSNSClient;
import com.example.springcqrsexample.common.listener.request.ArticleCommentPublishRequest;
import com.example.springcqrsexample.common.listener.request.ArticlePublishRequest;
import com.example.springcqrsexample.common.listener.request.UserPublishRequest;
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

    @Value("${cloud.aws.sns.arns.delete-article}")
    private String deleteArticleArn;

    @Value("${cloud.aws.sns.arns.delete-article-comment}")
    private String deleteArticleCommentArn;

    @Override
    public void onPostDelete(PostDeleteEvent event) {
        log.info("Emit `Delete` event");
        publishEvents(event.getEntity());
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister persister) {
        return false;
    }

    private void publishEvents(Object entity) {
        try {
            if (entity instanceof User) {
                publishUserEvent((User) entity);
            } else if (entity instanceof Article) {
                publishArticleEvent((Article) entity);
            } else if (entity instanceof ArticleComment) {
                publishArticleCommentEvent((ArticleComment) entity);
            }
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
    }

    private void publishUserEvent(User entity) throws JsonProcessingException {
        UserPublishRequest request = UserPublishRequest.builder()
            .userId(entity.getId())
            .build();
        awsSNSClient.publish("delete-user", deleteUserArn,
            objectMapper.writeValueAsString(request));
    }

    private void publishArticleEvent(Article entity) throws JsonProcessingException {
        ArticlePublishRequest request = ArticlePublishRequest.builder()
            .articleId(entity.getId())
            .build();
        awsSNSClient.publish("delete-article", deleteArticleArn,
            objectMapper.writeValueAsString(request));
    }

    private void publishArticleCommentEvent(ArticleComment entity) throws JsonProcessingException {
        ArticleCommentPublishRequest request = ArticleCommentPublishRequest.builder()
            .articleId(entity.getArticle().getId())
            .build();
        awsSNSClient.publish("delete-article-comment", deleteArticleCommentArn,
            objectMapper.writeValueAsString(request));
    }
}
