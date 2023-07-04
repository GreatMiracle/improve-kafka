package com.learn.kafka.kafkaconsumer.error;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GlobalErrorHandler implements CommonErrorHandler {
    @Override
    public void handleOtherException(Exception thrownException, Consumer<?, ?> consumer, MessageListenerContainer container, boolean batchListener) {
        log.warn("Handling other exception : {}", thrownException.getMessage());
//        CommonErrorHandler.super.handleOtherException(thrownException, consumer, container, batchListener);
    }

    @Override
    public void handleRecord(Exception thrownException, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer, MessageListenerContainer container) {
        log.warn("Handling exception for record : {}, because : {}", record.value(), thrownException.getMessage());
//        CommonErrorHandler.super.handleRecord(thrownException, record, consumer, container);
    }
}
