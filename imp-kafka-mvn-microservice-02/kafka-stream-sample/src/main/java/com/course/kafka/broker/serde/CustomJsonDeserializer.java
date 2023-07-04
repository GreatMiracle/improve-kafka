package com.course.kafka.broker.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class CustomJsonDeserializer <T> implements Deserializer<T> {

    private ObjectMapper objectMapper = new ObjectMapper();

    private Class<T> deserializedClass;

    public CustomJsonDeserializer(Class<T> promotionMessageClass) {
    }

    @Override
    public T deserialize(String s, byte[] bytes) {
        try {
            return objectMapper.readValue(bytes, deserializedClass);
        } catch (IOException e) {
            throw new SerializationException(e.getMessage());
        }
    }
}
