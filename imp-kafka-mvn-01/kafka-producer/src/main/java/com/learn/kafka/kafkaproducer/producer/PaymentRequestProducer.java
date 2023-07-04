package com.learn.kafka.kafkaproducer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learn.kafka.kafkacore.entity.PaymentRequest;
import com.learn.kafka.kafkacore.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

//@Component
public class PaymentRequestProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(PaymentRequest paymentRequest) throws JsonProcessingException {
        var json = JsonUtils.convertObjectToJson(paymentRequest);
        kafkaTemplate.send("t-payment-request", paymentRequest.getPaymentNumber(), json);
    }

}
