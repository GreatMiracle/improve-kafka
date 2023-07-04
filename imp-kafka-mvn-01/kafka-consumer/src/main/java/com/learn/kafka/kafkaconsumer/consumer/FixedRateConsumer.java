package com.learn.kafka.kafkaconsumer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class FixedRateConsumer {

    @KafkaListener(topics = "t-fixedrate")
    public void consume(String message) {
        log.info("COnsumer -This is topic t-fixedrate : {}", message);
    }

}
