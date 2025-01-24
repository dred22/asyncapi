package com.magomed.b.service;

import com.magomed.events.OrderCmd;
import io.github.springwolf.bindings.kafka.annotations.KafkaAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.magomed.events.enums.OrderCmdActionEnum.CREATE;

@RequiredArgsConstructor
@Service
public class OrderService {

    @Value("${spring.kafka.producer.order-cmd}")
    private String topic;
    private final KafkaTemplate<String, OrderCmd> kafkaTemplate;

    public void createWithName(String name) {
        OrderCmd orderCmd = new OrderCmd(CREATE, name, 1);
        sendCmd(orderCmd);
    }

    @AsyncPublisher(
            operation = @AsyncOperation(
                    channelName = "${spring.kafka.producer.order-cmd}",
                    description = "This producer sends the order command in order to manage (create, delete) orders"
            )
    )
    @KafkaAsyncOperationBinding
    private void sendCmd(OrderCmd orderCmd) {
        kafkaTemplate.send(topic, orderCmd);
    }
}
