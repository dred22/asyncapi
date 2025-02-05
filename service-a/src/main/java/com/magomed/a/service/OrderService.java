package com.magomed.a.service;

import com.magomed.a.data.OrderDto;
import com.magomed.a.data.UpdateOrderDto;
import com.magomed.a.entity.ArticleEntity;
import com.magomed.a.entity.OrderEntity;
import com.magomed.a.mapper.ArtAndOrdMapper;
import com.magomed.a.repository.ArticleRepository;
import com.magomed.a.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ArticleRepository articleRepository;
    private final ArtAndOrdMapper artAndOrdMapper;

    public List<OrderDto> getAll() {
        return orderRepository.findAll().stream()
                .map(artAndOrdMapper::orderEntityToDto).toList();
    }

    public OrderDto getById(int id) {
        return artAndOrdMapper.orderEntityToDto(orderRepository.findById(id).get());
    }

    @Transactional
    public OrderDto updateOrder(UpdateOrderDto orderUpdate) {

        List<ArticleEntity> articlesToAdd = new ArrayList<>();
        orderUpdate.articleNames().forEach(name->{
            var article = articleRepository.findByName(name)
                    .orElseGet(() -> ArticleEntity.builder()
                            .name(name)
                            .weight(1)
                            .build()
                    );
            articlesToAdd.add(article);
        });

        OrderEntity orderEntity = orderRepository.findById(orderUpdate.orderId()).get();
        orderEntity.setArticles(articlesToAdd);
        return artAndOrdMapper.orderEntityToDto(orderEntity);
    }
}
