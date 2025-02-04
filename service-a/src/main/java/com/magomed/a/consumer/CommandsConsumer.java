package com.magomed.a.consumer;

import com.magomed.a.service.CommandExecuteService;
import com.magomed.events.ArticleCmd;
import com.magomed.events.OrderCmd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CommandsConsumer {

    private final CommandExecuteService commandExecuteService;

    @KafkaListener(topics = "${spring.kafka.consumer.order-cmd}")
    public void orderCommandConsumer(OrderCmd orderCmd) {
        log.info("Cmd received [{}]", orderCmd);
        commandExecuteService.createOrder(orderCmd.articles());
    }

    @KafkaListener(topics = "${spring.kafka.consumer.article-cmd}")
    public void articleCommandConsumer(ArticleCmd articleCmd) {
        log.info("Cmd received [{}]", articleCmd);
        commandExecuteService.createArticle(articleCmd.article(), articleCmd.weight());
    }
}
