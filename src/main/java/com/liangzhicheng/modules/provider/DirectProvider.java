package com.liangzhicheng.modules.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import javax.annotation.Resource;

public class DirectProvider {

    private static final Logger logger = LoggerFactory.getLogger(DirectProvider.class);

    private static final String exchangeName = "exchange.direct";

    private final String[] keys = {"orange", "black", "green"};

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(int index) {
        StringBuffer buffer = new StringBuffer("Hello to ");
        int limitIndex = index % 3;
        String key = keys[limitIndex];
        buffer.append(key)
                .append(' ')
                .append(index + 1);
        String message = buffer.toString();
        rabbitTemplate.convertAndSend(exchangeName, key, message);
        logger.info("[direct pattern] send：{}", message);
    }

}
