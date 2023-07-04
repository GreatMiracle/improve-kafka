package com.learn.kafka.kafkaconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learn.kafka.kafkacore.entity.Commodity;
import com.learn.kafka.kafkacore.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class CommodityNotificationConsumer {

    @KafkaListener(topics = "t-commodity", groupId = "cg-notification")
    public void consume(String message) throws JsonProcessingException {
        var commodity = JsonUtils.convertJsonToObject(message, Commodity.class);
        log.info("This is topic t-commodity with Notification logic for : {}", commodity);
    }

}
