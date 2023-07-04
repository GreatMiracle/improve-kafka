package com.learn.kafka.kafkaconsumer.error;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component(value = "myFoodOrderErrorHandler")
@Slf4j
public class FoodOrderErrorHandler implements ConsumerAwareListenerErrorHandler {
    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception) {
        return ConsumerAwareListenerErrorHandler.super.handleError(message, exception);
    }

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException e, Consumer<?, ?> consumer) {
        log.warn("Food order error, sending to elasticsearch : {}, because : {}"
                , message.getPayload()
                , e.getMessage());
        return null;
    }
}
