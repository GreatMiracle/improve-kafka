package com.learn.kafka.kafkaconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learn.kafka.kafkacore.entity.Employee;
import com.learn.kafka.kafkacore.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class EmployeeJsonConsumer {

    @KafkaListener(topics = "t-employee")
    public void listen(String message) throws JsonProcessingException {
        var employee = JsonUtils.convertJsonToObject(message, Employee.class);
        log.info("This is topic t-employee with Employee is: {}", employee);
    }

}