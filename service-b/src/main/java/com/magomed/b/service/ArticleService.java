package com.magomed.b.service;

import com.magomed.events.ArticleCmd;
import io.github.springwolf.bindings.kafka.annotations.KafkaAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.magomed.events.enums.ArticleCmdActionEnum.CREATE;

@RequiredArgsConstructor
@Service
public class ArticleService {

    @Value("${spring.kafka.producer.article-cmd}")
    private String topic;
    private final KafkaTemplate<String, ArticleCmd> kafkaTemplate;

    public void createWithName(String name) {
        var articleCmd = new ArticleCmd(CREATE, name, 1);
        sendCmd(articleCmd);
    }

    @AsyncPublisher(
            operation = @AsyncOperation(
                    channelName = "${spring.kafka.producer.article-cmd}",
                    description = "This producer sends the article command in order to manage (create, delete) articles"
            )
    )
    @KafkaAsyncOperationBinding
    private void sendCmd(ArticleCmd articleCmd) {
        kafkaTemplate.send(topic, articleCmd);
    }
}
