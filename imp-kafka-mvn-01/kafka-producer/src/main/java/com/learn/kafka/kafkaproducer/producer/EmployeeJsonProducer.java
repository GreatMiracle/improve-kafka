package com.learn.kafka.kafkaproducer.producer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.learn.kafka.kafkacore.entity.Employee;
import com.learn.kafka.kafkacore.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

//@Component
public class EmployeeJsonProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(Employee employee) throws JsonProcessingException {
        var json = JsonUtils.convertObjectToJson(employee);
        kafkaTemplate.send("t-employee", json);
    }

}
