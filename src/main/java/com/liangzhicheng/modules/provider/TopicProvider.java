package com.liangzhicheng.modules.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import javax.annotation.Resource;

public class TopicProvider {

    private static final Logger logger = LoggerFactory.getLogger(TopicProvider.class);

    private static final String exchangeName = "exchange.topic";

    @Resource
    private RabbitTemplate rabbitTemplate;

    private final String[] keys = {"quick.orange.rabbit", "lazy.orange.elephant", "quick.orange.fox",
            "lazy.brown.fox", "lazy.pink.rabbit", "quick.brown.fox"};

    public void send(int index) {
        StringBuffer buffer = new StringBuffer("Hello to ");
        int limitIndex = index % keys.length;
        String key = keys[limitIndex];
        buffer.append(key)
                .append(' ')
                .append(index + 1);
        String message = buffer.toString();
        rabbitTemplate.convertAndSend(exchangeName, key, message);
        logger.info("[topic pattern] send：{}", message);
    }

}
