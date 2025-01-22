package com.magomed.b.consumer;

import com.magomed.events.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderEventsConsumer {


    @KafkaListener(topics = "${spring.kafka.consumer.order-events}")
    public void orderCommandConsumer(OrderEvent orderEvent) {
        log.info("Event received [{}]", orderEvent);
    }
}
