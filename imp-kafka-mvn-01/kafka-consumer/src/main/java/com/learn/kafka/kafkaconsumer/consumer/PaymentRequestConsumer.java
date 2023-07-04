package com.learn.kafka.kafkaconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.benmanes.caffeine.cache.Cache;
import com.learn.kafka.kafkacore.entity.PaymentRequest;
import com.learn.kafka.kafkacore.entity.PaymentRequestCacheKey;
import com.learn.kafka.kafkacore.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

//@Component
@Slf4j
public class PaymentRequestConsumer {

    @Autowired
    @Qualifier("cachePaymentRequest")
    private Cache<PaymentRequestCacheKey, Boolean> cache;

    private boolean isExistsInCache(PaymentRequestCacheKey key) {
        return Optional.ofNullable(cache.getIfPresent(key)).orElse(false);
    }

    @KafkaListener(topics = "t-payment-request")
    public void consume(String message) throws JsonProcessingException {
        var paymentRequest = JsonUtils.convertJsonToObject(message, PaymentRequest.class);

        var cacheKey = new PaymentRequestCacheKey(paymentRequest.getPaymentNumber()
                , paymentRequest.getAmount()
                , paymentRequest.getTransactionType());
        var processed = isExistsInCache(cacheKey);

        if (processed) {
            return;
        }

        log.info("Processing {}", paymentRequest);

        cache.put(cacheKey, true);
    }

}
