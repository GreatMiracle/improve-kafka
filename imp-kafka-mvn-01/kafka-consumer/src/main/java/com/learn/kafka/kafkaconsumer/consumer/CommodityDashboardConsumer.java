package com.learn.kafka.kafkaconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learn.kafka.kafkacore.entity.Commodity;
import com.learn.kafka.kafkacore.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

//@Component
@Slf4j
public class CommodityDashboardConsumer {

    @KafkaListener(topics = "t-commodity", groupId = "cg-dashboard")
    public void consume(String message) throws  JsonProcessingException, InterruptedException {
        var commodity = JsonUtils.convertJsonToObject(message, Commodity.class);

        var randomDelayMillis = ThreadLocalRandom.current().nextLong(500, 2000);
        TimeUnit.MILLISECONDS.sleep(randomDelayMillis);

        log.info("Dashboard logic for : {}", commodity);
    }

}