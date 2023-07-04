package com.learn.kafka.kafkaconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.benmanes.caffeine.cache.Cache;
import com.learn.kafka.kafkacore.entity.PurchaseRequest;
import com.learn.kafka.kafkacore.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

//@Component
@Slf4j
public class PurchaseRequestConsumer {

    @Autowired
    @Qualifier("cachePurchaseRequest")
    private Cache<Integer, Boolean> cache;

    private boolean isExistsInCache(int purchaseRequestId) {
        return Optional.ofNullable(cache.getIfPresent(purchaseRequestId)).orElse(false);
    }

    @KafkaListener(topics = "t-purchase-request")
    public void consume(String message) throws  JsonProcessingException {
        var purchaseRequest = JsonUtils.convertJsonToObject(message, PurchaseRequest.class);

        var processed = isExistsInCache(purchaseRequest.getId());

        if (processed) {
            return;
        }

        log.info("Processing {}", purchaseRequest);

        cache.put(purchaseRequest.getId(), true);
    }

}
