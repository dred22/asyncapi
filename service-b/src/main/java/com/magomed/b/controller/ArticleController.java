package com.magomed.b.controller;

import com.magomed.b.model.ArticleDto;
import com.magomed.b.service.ArticleService;
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
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping
    public void create(@RequestBody ArticleDto articleDto) {
        log.info("Create order [{}]", articleDto);
        articleService.createWithName(articleDto.name());
    }
}
