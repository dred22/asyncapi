package com.magomed.b.controller;

import com.magomed.b.model.OrderDto;
import com.magomed.b.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public void create(@RequestBody OrderDto orderDto){
        log.info("Create order [{}]", orderDto);
        orderService.createWithName(orderDto.names());
    }
}
