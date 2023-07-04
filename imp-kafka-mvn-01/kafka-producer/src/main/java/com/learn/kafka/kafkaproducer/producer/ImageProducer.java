package com.learn.kafka.kafkaproducer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learn.kafka.kafkacore.entity.Image;
import com.learn.kafka.kafkacore.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ImageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(Image image, int partition) throws JsonProcessingException {
        var json = JsonUtils.convertObjectToJson(image);
        kafkaTemplate.send("t-image", partition, image.getType(), json);
        log.info("ImageProducer send topic t-image Iamge {}", json);
    }

}
