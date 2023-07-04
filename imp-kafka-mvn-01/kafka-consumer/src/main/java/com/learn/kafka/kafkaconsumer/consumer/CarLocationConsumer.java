package com.learn.kafka.kafkaconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learn.kafka.kafkacore.entity.CarLocation;
import com.learn.kafka.kafkacore.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class CarLocationConsumer {

    @KafkaListener(topics = "t-location", groupId = "cg-all-location")
    public void listenAll(String message) throws JsonProcessingException {
        var carLocation = JsonUtils.convertJsonToObject(message, CarLocation.class);
        log.info("This is topic t-location and listenAll : {}", carLocation);
    }


    @KafkaListener(topics = "t-location", groupId = "cg-far-location",containerFactory = "farLocationContainerFactory")
    public void listenFar(String message) throws JsonProcessingException {
        var carLocation = JsonUtils.convertJsonToObject(message, CarLocation.class);
        log.info("This is topic t-location and listenFar: {}", carLocation);
    }
}
