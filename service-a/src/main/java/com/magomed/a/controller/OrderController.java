package com.magomed.a.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/manage")
public class OrderController {

    @PostMapping
    public void create(@RequestBody String message){
        //log.info("Push event [{}]", message);
        //orderService.createWithName(orderDtp.name());
    }
}