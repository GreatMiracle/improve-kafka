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
public class PurchaseRequest {
    int id;
    String prNumber;
    int amount;
    String currency;
}