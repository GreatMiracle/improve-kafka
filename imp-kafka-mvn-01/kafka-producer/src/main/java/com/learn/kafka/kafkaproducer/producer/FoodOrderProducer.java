package com.learn.kafka.kafkaproducer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learn.kafka.kafkacore.entity.FoodOrder;
import com.learn.kafka.kafkacore.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class FoodOrderProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(FoodOrder foodOrder) throws JsonProcessingException {
        var json = JsonUtils.convertObjectToJson(foodOrder);

        kafkaTemplate.send("t-food-order", json);
        log.info("Producer send to kafka topic t-food-order {}",  json);
    }

}
