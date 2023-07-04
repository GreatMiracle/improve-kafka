package com.course.kafka.consumer;

import com.course.kafka.message.OrderMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderConsumer {

    @KafkaListener(topics = "t-commodity-order")
    public void listen(OrderMessage message){
        var totalItemAmount = message.getPrice()  * message.getQuantity();
        log.info("Processing order {}, item {}, credit card number {}. Total amount for this item is {}",
                message.getOrderNumber(), message.getItemName(), message.getCreditCardNumber(), totalItemAmount );
    }
}
