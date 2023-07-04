package com.course.kafka.broker.producer;

import com.course.kafka.message.PromotionMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class PromotionProducer {
    @Autowired
    private KafkaTemplate<String, PromotionMessage> kafkaTemplate;

    public void publish(PromotionMessage message) {
        try {
            var sendResult = kafkaTemplate.send("t-commodity-promotion", message).get();
            log.info("Send result success for message {}", sendResult.getProducerRecord().value());
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error publishing {}, because {}", message, e.getMessage());
        }
    }
}
