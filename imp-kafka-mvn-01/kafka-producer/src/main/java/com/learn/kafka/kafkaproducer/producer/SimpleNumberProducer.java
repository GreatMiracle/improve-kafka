package com.learn.kafka.kafkaproducer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learn.kafka.kafkacore.entity.SimpleNumber;
import com.learn.kafka.kafkacore.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

//@Component
public class SimpleNumberProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(SimpleNumber simpleNumber) throws JsonProcessingException {
        var json = JsonUtils.convertObjectToJson(simpleNumber);
        kafkaTemplate.send("t-simple-number", json);
    }
}
