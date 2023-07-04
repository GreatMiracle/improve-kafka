package com.course.kafka.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    @Id
    @GeneratedValue
    int orderId;

    @Column
    String orderNumber;

    @Column
    String orderLocation;

    @Column
    LocalDateTime orderDateTime;

    @Column
    String creditCardNumber;

    @OneToMany(mappedBy = "order")
    List<OrderItem> items;
}
