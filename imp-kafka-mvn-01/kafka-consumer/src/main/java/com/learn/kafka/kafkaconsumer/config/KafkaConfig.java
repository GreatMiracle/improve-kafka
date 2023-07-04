package com.learn.kafka.kafkaconsumer.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learn.kafka.kafkaconsumer.error.GlobalErrorHandler;
import com.learn.kafka.kafkacore.entity.CarLocation;
import com.learn.kafka.kafkacore.utils.JsonUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public ConsumerFactory<Object, Object> consumerFactory() {
        var properties = kafkaProperties.buildConsumerProperties();

        properties.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG, "120000");

        return new DefaultKafkaConsumerFactory<>(properties);
    }

    @Bean(name = "farLocationContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<Object, Object> farLocationContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer) {
        var factory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
        configurer.configure(factory, consumerFactory());

        factory.setRecordFilterStrategy(new RecordFilterStrategy<Object, Object>() {

            @Override
            public boolean filter(ConsumerRecord<Object, Object> consumerRecord) {
                try {
                    CarLocation carLocation = JsonUtils.convertJsonToObject(consumerRecord.value().toString(),
                            CarLocation.class);
                    return carLocation.getDistance() <= 100;
                } catch (JsonProcessingException e) {
                    return false;
                }
            }
        });

        return factory;
    }


    @Bean(name = "kafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<Object, Object> kafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer) {
        var factory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
        configurer.configure(factory, consumerFactory());

        factory.setCommonErrorHandler(new GlobalErrorHandler());

        return factory;
    }

    @Bean(name = "imageRetryContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<Object, Object> imageRetryContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer) {
        var factory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
        configurer.configure(factory, consumerFactory());

        factory.setCommonErrorHandler(new DefaultErrorHandler(new FixedBackOff(10_000, 3)));

        return factory;
    }


    @Bean(name = "invoiceDltContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<Object, Object> invoiceDltContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer, KafkaTemplate<String, String> kafkaTemplate) {
        var factory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
        configurer.configure(factory, consumerFactory());

        var recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate);

        factory.setCommonErrorHandler(new DefaultErrorHandler(recoverer, new FixedBackOff(1000, 5)));

        return factory;
    }

}
