package com.learn.kafka.kafkaproducer.scheduler;

import java.util.List;

import com.learn.kafka.kafkacore.entity.Commodity;
import com.learn.kafka.kafkaproducer.producer.CommodityProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;


import com.fasterxml.jackson.core.JsonProcessingException;

//@Component
public class CommodityScheduler {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private CommodityProducer producer;

    @Scheduled(fixedRate = 5000)
    public void fetchCommodities() {
        var commodities = restTemplate.exchange("http://localhost:8091/api/commodity/v1/all", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Commodity>>() {
                }).getBody();

        commodities.forEach(t -> {
            try {
                producer.sendMessage(t);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }

}
