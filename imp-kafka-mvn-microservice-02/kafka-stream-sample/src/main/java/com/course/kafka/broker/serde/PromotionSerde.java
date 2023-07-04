package com.course.kafka.broker.serde;

import com.course.kafka.message.PromotionMessage;

public class PromotionSerde extends CustomJsonSerde<PromotionMessage> {
    public PromotionSerde() {
        super(new CustomJsonSerializer<PromotionMessage>(),
                new CustomJsonDeserializer<PromotionMessage>(PromotionMessage.class));
    }
}
