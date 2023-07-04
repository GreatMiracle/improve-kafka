package com.course.kafka.service;

import com.course.kafka.broker.producer.DiscountProducer;
import com.course.kafka.controller.dtos.request.DiscountRequest;
import com.course.kafka.message.DiscountMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final DiscountProducer producer;

    public void createDiscount(DiscountRequest request) {
        var message = new DiscountMessage(request.getDiscountCode(), request.getDiscountPercentage());
        producer.publish(message);
    }
}
