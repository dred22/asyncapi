package com.magomed.a.service;

import com.magomed.events.OrderEvent;
import io.github.springwolf.bindings.kafka.annotations.KafkaAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.magomed.events.enums.OrderEventTypeEnum.CREATED;

@RequiredArgsConstructor
@Service
public class OrderExecuteService {

    @Value("${spring.kafka.producer.order-events}")
    private String topic;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void createOrder(String article) {
        var orderEvent = new OrderEvent(article, "successfully executed", CREATED);
        sendEvent(orderEvent);
    }

    @AsyncPublisher(
            operation = @AsyncOperation(
                    channelName = "${spring.kafka.producer.order-events}",
                    description = "This producer sends the order event in order to confirm command execution"
            )
    )
    @KafkaAsyncOperationBinding
    private void sendEvent(OrderEvent orderEvent) {
        kafkaTemplate.send(topic, orderEvent);
    }
}
