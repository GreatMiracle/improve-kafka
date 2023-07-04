package com.learn.kafka.kafkaproducer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learn.kafka.kafkacore.entity.CarLocation;
import com.learn.kafka.kafkacore.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

//@Component
public class CarLocationProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(CarLocation carLocation) throws JsonProcessingException {
        var json = JsonUtils.convertObjectToJson(carLocation);
        kafkaTemplate.send("t-location", json);
    }

}
