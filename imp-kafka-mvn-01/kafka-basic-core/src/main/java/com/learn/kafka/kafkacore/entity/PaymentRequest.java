package com.learn.kafka.kafkacore.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentRequest {
    String paymentNumber;
    int amount;
    String currency;
    String notes;
    String transactionType;
}

