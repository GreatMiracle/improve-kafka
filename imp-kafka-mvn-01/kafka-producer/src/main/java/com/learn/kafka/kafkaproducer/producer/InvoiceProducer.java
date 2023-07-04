package com.learn.kafka.kafkaproducer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learn.kafka.kafkacore.entity.Invoice;
import com.learn.kafka.kafkacore.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class InvoiceProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(Invoice invoice) throws JsonProcessingException {
        var json = JsonUtils.convertObjectToJson(invoice);
        kafkaTemplate.send("t-invoice", invoice.getAmount() % 2, invoice.getInvoiceNumber(), json);
    }

}
