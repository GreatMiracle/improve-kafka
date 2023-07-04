package com.course.kafka.service;

import com.course.kafka.broker.producer.PromotionProducer;
import com.course.kafka.controller.dtos.request.PromotionRequest;
import com.course.kafka.message.PromotionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionProducer producer;

    public void createPromotion(PromotionRequest request) {
        var message = new PromotionMessage(request.getPromotionCode());

        producer.publish(message);
    }
}
