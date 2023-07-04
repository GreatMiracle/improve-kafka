package com.course.kafka.consumer;

import com.course.kafka.message.PromotionMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PromotionUppercaseListener {
    @KafkaListener(topics = "t-commodity-promotion-uppercase")
    public void listenPromotionUppercase(PromotionMessage message) {
        log.info("Processing uppercase promotion : {}", message);
    }
}
