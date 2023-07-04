package com.course.kafka.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_items")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItem {
    @Id
    @GeneratedValue
    private int orderItemId;

    @Column
    private String itemName;

    @Column
    private int price;

    @Column
    private int quantity;

    @JoinColumn(name = "order_id")
    @ManyToOne
    private Order order;
}
