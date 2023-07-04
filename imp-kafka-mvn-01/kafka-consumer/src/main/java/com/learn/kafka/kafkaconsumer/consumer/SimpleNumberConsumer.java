package com.learn.kafka.kafkaconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learn.kafka.kafkacore.entity.SimpleNumber;
import com.learn.kafka.kafkacore.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SimpleNumberConsumer {
    @KafkaListener(topics = "t-simple-number", containerFactory = "kafkaListenerContainerFactory")
    public void consume(String message) throws JsonProcessingException {
        var simpleNumber = JsonUtils.convertJsonToObject(message, SimpleNumber.class);

        if (simpleNumber.getNumber() %2 != 0) {
            throw new IllegalArgumentException("Odd number : " + simpleNumber.getNumber());
        }

        log.info("Processing simpleNumber : {}", simpleNumber);
    }
}
