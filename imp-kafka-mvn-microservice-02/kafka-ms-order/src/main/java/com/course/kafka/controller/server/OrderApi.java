package com.course.kafka.controller.server;

import com.course.kafka.controller.dtos.request.OrderRequest;
import com.course.kafka.controller.dtos.response.OrderResponse;
import com.course.kafka.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderApi {
    private final OrderService orderService;

    @PostMapping()
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        // 1. save order using service
        var orderNumber = orderService.saveOrder(request);

        // 2. create response
        var orderResponse = new OrderResponse(orderNumber);

        // 3. return response
        return ResponseEntity.ok().body(orderResponse);
    }
}
