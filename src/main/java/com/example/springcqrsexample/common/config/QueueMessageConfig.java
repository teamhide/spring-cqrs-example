package com.example.springcqrsexample.common.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.PayloadMethodArgumentResolver;

import java.util.Collections;

@Configuration
public class QueueMessageConfig {

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.sqs.url}")
    private String sqsUrl;

    @Bean
    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(
        AmazonSQSAsync amazonSQS) {
        SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
        simpleAsyncTaskExecutor.setConcurrencyLimit(50);

        SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
        factory.setAmazonSqs(amazonSQS);
        factory.setMaxNumberOfMessages(10);
        factory.setWaitTimeOut(10);
        factory.setVisibilityTimeout(30);
        factory.setTaskExecutor(simpleAsyncTaskExecutor);
        return factory;
    }

    @Bean
    @Profile("local")
    @Primary
    public AmazonSQSAsync localAmazonSQSAsync() {
        return AmazonSQSAsyncClientBuilder.standard()
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(sqsUrl, region))
            .withCredentials(
                new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
            .build();
    }

//    @Bean
//    public QueueMessageHandlerFactory queueMessageHandlerFactory(AmazonSQSAsync amazonSQS) {
//        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
//        messageConverter.setStrictContentTypeMatch(false);
//        messageConverter.setObjectMapper(objectMapper());
//
//        QueueMessageHandlerFactory factory = new QueueMessageHandlerFactory();
//        factory.setAmazonSqs(amazonSQS);
//        factory.setArgumentResolvers(Collections.singletonList(new PayloadMethodArgumentResolver(messageConverter)));
//        return factory;
//    }
//
//    private ObjectMapper objectMapper() {
//        return Jackson2ObjectMapperBuilder.json()
//                .modules(new JavaTimeModule())
//                .build();
//    }
}
