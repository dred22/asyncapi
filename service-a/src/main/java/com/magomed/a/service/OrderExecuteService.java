package com.magomed.a.service;

import com.magomed.events.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderExecuteService {

    @Value("${spring.kafka.producer.order-events}")
    private String topic;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void createOrder(String article) {
        var orderEvent = new OrderEvent(article, "CREATED");
        kafkaTemplate.send(topic, orderEvent);
    }
}
