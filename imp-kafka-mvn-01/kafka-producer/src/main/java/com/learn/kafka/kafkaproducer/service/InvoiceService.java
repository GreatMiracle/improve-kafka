package com.learn.kafka.kafkaproducer.service;

import com.learn.kafka.kafkacore.entity.Invoice;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class InvoiceService {
    private final AtomicInteger counter = new AtomicInteger();

    public Invoice generateInvoice() {
        var invoiceNumber = "INV-" + counter.incrementAndGet();
        var amount = ThreadLocalRandom.current().nextInt(1, 1000);

        return new Invoice(invoiceNumber, amount, "USD");
    }
}
