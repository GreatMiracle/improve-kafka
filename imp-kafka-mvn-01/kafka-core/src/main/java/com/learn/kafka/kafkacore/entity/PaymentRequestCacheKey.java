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
public class PaymentRequestCacheKey {
    String paymentNumber;
    int amount;
    String transactionType;
}
