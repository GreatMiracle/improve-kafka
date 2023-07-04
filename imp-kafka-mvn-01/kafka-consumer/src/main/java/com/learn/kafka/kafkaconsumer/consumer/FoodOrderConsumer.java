package com.learn.kafka.kafkaconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learn.kafka.kafkacore.entity.FoodOrder;
import com.learn.kafka.kafkacore.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FoodOrderConsumer {

    private static final int MAX_ORDER_AMOUNT = 7;

    @KafkaListener(topics = "t-food-order", errorHandler = "myFoodOrderErrorHandler")
    public void consume(String message) throws JsonProcessingException {
        var foodOrder = JsonUtils.convertJsonToObject(message, FoodOrder.class);
        if (foodOrder.getAmount() > MAX_ORDER_AMOUNT) {
            throw new IllegalArgumentException("Order amount is too many : " + foodOrder.getAmount());
        }

        log.info("This is topic t-food-order, Processing food order : {}", foodOrder);
    }


}
