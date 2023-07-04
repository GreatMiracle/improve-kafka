package com.course.kafka.controller.server;

import com.course.kafka.controller.dtos.request.PromotionRequest;
import com.course.kafka.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/promotion")
@RequiredArgsConstructor
public class PromotionApi {

    private final PromotionService service;

    @PostMapping()
    public ResponseEntity<String> create(@RequestBody PromotionRequest request) {
        service.createPromotion(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(request.getPromotionCode());
    }
}
