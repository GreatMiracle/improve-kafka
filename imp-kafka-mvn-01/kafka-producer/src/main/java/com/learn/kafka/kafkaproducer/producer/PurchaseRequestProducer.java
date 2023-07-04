package com.learn.kafka.kafkaproducer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learn.kafka.kafkacore.entity.PurchaseRequest;
import com.learn.kafka.kafkacore.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

//@Component
public class PurchaseRequestProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(PurchaseRequest purchaseRequest) throws JsonProcessingException {
        var json = JsonUtils.convertObjectToJson(purchaseRequest);
        kafkaTemplate.send("t-purchase-request", purchaseRequest.getPrNumber(), json);
    }

}
