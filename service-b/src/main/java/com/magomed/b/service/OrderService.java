package com.magomed.b.service;

import com.magomed.events.OrderCmd;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    @Value("${spring.kafka.producer.order-cmd}")
    private String topic;
    private final KafkaTemplate<String, OrderCmd> kafkaTemplate;

    public void createWithName(String name) {
        OrderCmd orderCmd = new OrderCmd("CREATE", name, 1);
        kafkaTemplate.send(topic, orderCmd);
    }
}
