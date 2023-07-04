package com.course.kafka.consumer;

import com.course.kafka.message.OrderMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
@Slf4j
public class OrderConsumer {
    @KafkaListener(topics = "t-commodity-order")
    public void listen(ConsumerRecord<String, OrderMessage> consumerRecord) {
        var headers = consumerRecord.headers();
        var orderMessage =  consumerRecord.value();

        log.info("Processing order {},item {}, credit card number {}", orderMessage.getOrderNumber(),
                orderMessage.getItemName(), orderMessage.getCreditCardNumber());

        log.info("Headers : ");
        headers.forEach(
                h -> log.info("  key : {}, value : {}",
                        h.key(), new String(h.value()))
        );

        var headerValue = ObjectUtils.isEmpty(headers.lastHeader("surpriseBonus").value()) ? "0"
                : new String(headers.lastHeader("surpriseBonus").value());

        var bonusPercentage = Integer.parseInt(headerValue);
        var bonusAmount = (bonusPercentage / 100d) * orderMessage.getPrice() * orderMessage.getQuantity();

        log.info("Bonus amount is {}", bonusAmount);
    }
}
