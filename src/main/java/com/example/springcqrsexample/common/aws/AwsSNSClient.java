package com.example.springcqrsexample.common.aws;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class AwsSNSClient {
    private final AmazonSNS amazonSNS;

    public void publish(String subject, String topicArn, String message) {
        HashMap<String, MessageAttributeValue> attributes = new HashMap<>();
        attributes.put("contentType", new MessageAttributeValue()
                .withDataType("String")
                .withStringValue("application/json")
        );
        PublishRequest publishRequest = new PublishRequest()
                .withSubject(subject)
                .withTopicArn(topicArn)
                .withMessageAttributes(attributes)
                .withMessage(message);

        amazonSNS.publish(publishRequest);
    }
}
