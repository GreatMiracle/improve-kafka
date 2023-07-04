package com.learn.kafka.kafkaproducer.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

//@Component
@Slf4j
public class FixedRateProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final AtomicInteger counter = new AtomicInteger();

    @Scheduled(fixedRate = 1000)
    public void sendMessage() {
        var i = counter.incrementAndGet();
        log.info("This is i at topic t-fixedrate: i is " + i);
        kafkaTemplate.send("t-fixedrate", "Fixed rate " + i);
    }

}