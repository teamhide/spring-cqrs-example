package com.example.springcqrsexample.common.aws;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AwsSNSClient {
    private final AmazonSNS amazonSNS;

    public void publish(String topicArn, String message) {
        PublishRequest publishRequest = new PublishRequest()
                .withTopicArn(topicArn)
                .withMessage(message);

        amazonSNS.publish(publishRequest);
    }
}
