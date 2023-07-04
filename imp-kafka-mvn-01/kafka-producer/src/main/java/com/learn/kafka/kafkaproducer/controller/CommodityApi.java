package com.learn.kafka.kafkaproducer.controller;

import com.learn.kafka.kafkacore.entity.Commodity;
import com.learn.kafka.kafkaproducer.service.CommodityService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/commodity/v1")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CommodityApi {

    CommodityService commodityService;

    @GetMapping(value = "/all")
    public List<Commodity> generateAllCommodities() {
        return commodityService.createDummyCommodities();
    }

}
