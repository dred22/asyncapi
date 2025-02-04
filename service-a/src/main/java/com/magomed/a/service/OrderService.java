package com.magomed.a.service;

import com.magomed.a.data.OrderDto;
import com.magomed.a.mapper.ArtAndOrdMapper;
import com.magomed.a.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ArtAndOrdMapper artAndOrdMapper;

    public List<OrderDto> getAll() {
        return orderRepository.findAll().stream()
                .map(artAndOrdMapper::orderEntityToDto).toList();
    }

    public OrderDto getById(int id) {
        return artAndOrdMapper.orderEntityToDto(orderRepository.findById(id).get());
    }
}
