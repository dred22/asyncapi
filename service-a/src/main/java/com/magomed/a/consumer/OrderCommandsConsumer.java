package com.magomed.a.consumer;

import com.magomed.a.service.OrderExecuteService;
import com.magomed.events.OrderCmd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderCommandsConsumer {

    private final OrderExecuteService orderExecuteService;

    @KafkaListener(topics = "${spring.kafka.consumer.order-cmd}")
    public void orderCommandConsumer(OrderCmd orderCmd) {
        log.info("Cmd received [{}]", orderCmd);
        orderExecuteService.createOrder(orderCmd.article());
    }
}
