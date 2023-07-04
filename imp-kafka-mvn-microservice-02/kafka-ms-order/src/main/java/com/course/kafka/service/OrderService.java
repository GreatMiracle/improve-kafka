package com.course.kafka.service;

import com.course.kafka.broker.producer.OrderProducer;
import com.course.kafka.controller.dtos.request.OrderItemRequest;
import com.course.kafka.controller.dtos.request.OrderRequest;
import com.course.kafka.entity.Order;
import com.course.kafka.entity.OrderItem;
import com.course.kafka.message.OrderMessage;
import com.course.kafka.repository.OrderItemRepository;
import com.course.kafka.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderProducer orderProducer;

    public String saveOrder(OrderRequest request) {
        List<OrderItem> orderItemList = new ArrayList<>();

        request.getItems().forEach(
            i -> {
                orderItemList.add(OrderItem.builder()
                        .itemName(i.getItemName())
                        .price(i.getPrice())
                        .quantity(i.getQuantity())
                        .build()
                );
            }
        );

        var result = Order.builder()
                                    .creditCardNumber(request.getCreditCardNumber())
                                    .orderLocation(request.getOrderLocation())
                                    .orderDateTime(LocalDateTime.now())
                                    .orderNumber(RandomStringUtils.randomAlphanumeric(8).toUpperCase())
                                    .items(orderItemList)
                                    .build();

        Order orderSaved = orderRepository.save(result);
        orderItemRepository.saveAll(orderItemList);

        orderItemList.forEach(
                i -> {
                    if(i.getOrderItemId() == orderSaved.getOrderId()){
                        i.setOrder(orderSaved);
                    }
                }
        );

        orderItemList.forEach(this::publishToKafka);

        return result.getOrderNumber();
    }

    public void publishToKafka(OrderItem orderItem) {
        var order = orderItem.getOrder();
        var orderMessage = OrderMessage.builder()
                .itemName(orderItem.getItemName())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .orderDateTime(order.getOrderDateTime())
                .orderLocation(order.getOrderLocation())
                .orderNumber(order.getOrderNumber())
                .creditCardNumber(order.getCreditCardNumber())
                .build();

        orderProducer.publish(orderMessage);
    }
}
