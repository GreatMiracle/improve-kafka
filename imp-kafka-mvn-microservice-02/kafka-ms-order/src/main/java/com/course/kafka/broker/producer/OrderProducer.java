package com.course.kafka.broker.producer;

import com.course.kafka.message.OrderMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class OrderProducer {
    @Autowired
    private KafkaTemplate<String, OrderMessage> kafkaTemplate;


    public void publish (OrderMessage message) {
        //callback not support new kafka version
//        kafkaTemplate.send("t-comodity-order", message.getOrderNumber(), message)
//                .addCallback(new ListenableFutureCallback<SendResult<String, OrderMessage>>() {
//
//                    @Override
//                    public void onSuccess(SendResult<String, OrderMessage> result) {
//                        LOG.info("Order {}, item {} published succesfully", message.getOrderNumber(),
//                                message.getItemName());
//                    }
//
//                    @Override
//                    public void onFailure(Throwable ex) {
//                        LOG.warn("Order {}, item {} failed to publish because {}", message.getOrderNumber(),
//                                message.getItemName(), ex.getMessage());
//                    }
//                });
//
//        LOG.info("Just a dummy message for order {}, item {}", message.getOrderNumber(), message.getItemName());


        var producerRecord = buildProducerRecord(message);


//        kafkaTemplate.send("t-commodity-order", message.getOrderNumber(), message)
        kafkaTemplate.send(producerRecord)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        // Xử lý khi gửi thông điệp thất bại
                        log.info("Order {}, item {} published succesfully", message.getOrderNumber()
                                , message.getItemName());
                    } else {
                        // Xử lý khi gửi thông điệp thành cônglog
                        log.warn("Order {}, item {} failed to publish because {}", message.getOrderNumber(),
                                message.getItemName(), ex.getMessage());
                    }
                });

        log.info("Just a dummy message for order {}, item {}", message.getOrderNumber(), message.getItemName());
    }

    private ProducerRecord<String, OrderMessage> buildProducerRecord(OrderMessage message) {
        var surpriseBonus= StringUtils.startsWithIgnoreCase(message.getOrderLocation(), "A") ? 25 : 15;
        List<Header> headers = new ArrayList<>();
        var surpriseBonusHeader = new RecordHeader("surpriseBonus", Integer.toString(surpriseBonus).getBytes());

        headers.add(surpriseBonusHeader);

        return new ProducerRecord<String, OrderMessage>("t-commodity-order", null, message.getOrderNumber(), message, headers);
    }
    
}
