package com.magomed.a.controller;

import com.magomed.a.data.ArticleDto;
import com.magomed.a.data.OrderDto;
import com.magomed.a.data.UpdateOrderDto;
import com.magomed.a.repository.OrderRepository;
import com.magomed.a.service.ArticleService;
import com.magomed.a.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/manage")
public class OrderController {

    private final OrderRepository orderRepository;
    private final ArticleService articleService;
    private final OrderService orderService;

    @GetMapping("/orders")
    public List<OrderDto> getOrders() {
        return orderService.getAll();
    }

    @GetMapping("/orders/{id}")
    public OrderDto getOrder(@PathVariable int id) {
        return orderService.getById(id);
    }

    @PatchMapping("/orders")
    public OrderDto updatedOrder(@RequestBody UpdateOrderDto orderUpdate) {
        return orderService.updateOrder(orderUpdate);
    }

    @GetMapping("/articles")
    public List<ArticleDto> getArticles() {
        return articleService.getAll();
    }

    @GetMapping("/articles/{id}")
    public ArticleDto getArticle(@PathVariable int id) {
        return articleService.getById(id);
    }

}