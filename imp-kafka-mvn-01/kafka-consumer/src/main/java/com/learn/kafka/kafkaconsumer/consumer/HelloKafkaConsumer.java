package com.learn.kafka.kafkaconsumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
public class HelloKafkaConsumer {

    @KafkaListener(topics = "t-hello")
    public void consume(String message) {
        System.out.println("This is topic hello-kafka: " + message);
    }

}
