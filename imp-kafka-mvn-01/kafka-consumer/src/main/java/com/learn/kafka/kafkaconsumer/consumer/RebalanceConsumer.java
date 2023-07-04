package com.learn.kafka.kafkaconsumer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class RebalanceConsumer {

    @KafkaListener(topics = "t-rebalance", concurrency = "3")
    public void consume(ConsumerRecord<String, String> consumerRecord) {
        log.info("This is topic t-rebalance, Partition : {}, Offset : {}, Message : {}"
                , consumerRecord.partition()
                , consumerRecord.offset()
                , consumerRecord.value());
    }

}
