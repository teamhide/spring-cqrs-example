package com.example.springcqrsexample.consumer;

import org.springframework.cloud.aws.messaging.listener.Acknowledgment;

public interface SQSConsumer<T> {
    void consumeCreate(T message, Acknowledgment ack);
    void consumeUpdate(T message, Acknowledgment ack);
    void consumeDelete(T message, Acknowledgment ack);
}
