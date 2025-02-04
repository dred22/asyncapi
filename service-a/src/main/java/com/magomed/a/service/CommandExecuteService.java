package com.magomed.a.service;

import com.magomed.a.entity.ArticleEntity;
import com.magomed.a.entity.OrderEntity;
import com.magomed.a.repository.ArticleRepository;
import com.magomed.a.repository.OrderRepository;
import com.magomed.events.OrderEvent;
import io.github.springwolf.bindings.kafka.annotations.KafkaAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.magomed.events.enums.OrderEventTypeEnum.CREATED;

@RequiredArgsConstructor
@Service
public class CommandExecuteService {

    @Value("${spring.kafka.producer.order-events}")
    private String topic;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private final ArticleRepository articleRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public void createOrder(List<String> articles) {
        var order = OrderEntity.builder()
                .amount(1)
                .build();
        order.setArticles(articleRepository.findAllByNameIn(articles));
        OrderEntity savedOrder = orderRepository.save(order);
        var articleNames = savedOrder.getArticles().stream().map(ArticleEntity::getName).toList();
        var orderEvent = new OrderEvent(articleNames, "successfully executed", CREATED);
        sendEvent(orderEvent);
    }

    public void createArticle(String name, int weight){
        var article = ArticleEntity.builder()
                .name(name)
                .weight(weight)
                .build();
        articleRepository.save(article);
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
