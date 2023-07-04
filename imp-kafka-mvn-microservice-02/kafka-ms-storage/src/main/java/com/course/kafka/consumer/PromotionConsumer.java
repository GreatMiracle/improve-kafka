package com.course.kafka.consumer;

import com.course.kafka.message.DiscountMessage;
import com.course.kafka.message.PromotionMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@KafkaListener(topics = "t-commodity-promotion")
public class PromotionConsumer {

    @KafkaHandler
    public void listenPromotion(PromotionMessage message) {
        log.info("Processing promotion : {}", message);
    }

    @KafkaHandler
    public void listenDiscount(DiscountMessage message) {
        log.info("Processing discount : {}", message);
    }

}
