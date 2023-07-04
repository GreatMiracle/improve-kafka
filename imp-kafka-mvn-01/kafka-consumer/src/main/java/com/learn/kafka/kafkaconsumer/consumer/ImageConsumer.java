package com.learn.kafka.kafkaconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learn.kafka.kafkacore.entity.Image;
import com.learn.kafka.kafkacore.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ImageConsumer {

    @KafkaListener(topics = "t-image", concurrency = "2" , containerFactory = "imageRetryContainerFactory")
    public void consumer (ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {
        var image = JsonUtils.convertJsonToObject(consumerRecord.value(), Image.class);

        if (image.getType().equalsIgnoreCase("svg")) {
            log.warn("Throwing exception on partition {} for image {}", consumerRecord.partition(), image);
            throw new IllegalArgumentException("Simulate API call failed");
        }

        log.info("This is consumer of topic t-image, partition{} - json Image {}", consumerRecord.partition(), image);

    }

}
