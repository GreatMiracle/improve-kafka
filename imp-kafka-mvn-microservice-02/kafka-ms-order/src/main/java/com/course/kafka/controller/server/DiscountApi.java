package com.course.kafka.controller.server;

import com.course.kafka.controller.dtos.request.DiscountRequest;
import com.course.kafka.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/discount")
@RequiredArgsConstructor
public class DiscountApi {
    private final DiscountService service;

    @PostMapping()
    public ResponseEntity<String> create(@RequestBody DiscountRequest request) {
        service.createDiscount(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(request.getDiscountCode() + " with " + request.getDiscountPercentage() + "% discount");
    }
}
