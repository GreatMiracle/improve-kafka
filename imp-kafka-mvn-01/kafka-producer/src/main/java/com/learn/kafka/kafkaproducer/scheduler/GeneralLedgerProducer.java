package com.learn.kafka.kafkaproducer.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class GeneralLedgerProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final AtomicInteger counter = new AtomicInteger();

    public void send(String message) {
        kafkaTemplate.send("t-general-ledger",message);
    }

    @Scheduled(fixedRate = 1000)
    public void schedule() {
        var message = "Ledger " + counter.incrementAndGet();
        send(message);
    }

}
