package com.liangzhicheng.modules.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import javax.annotation.Resource;

public class FanoutProvider {

    private static final Logger logger = LoggerFactory.getLogger(FanoutProvider.class);

    private static final String exchangeName = "exchange.fanout";

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(int index) {
        StringBuffer builder = new StringBuffer("Hello");
        int limitIndex = index % 3 + 1;
        for (int i = 0; i < limitIndex; i++) {
            builder.append('.');
        }
        builder.append(index + 1);
        String message = builder.toString();
        rabbitTemplate.convertAndSend(exchangeName, "", message);
        logger.info("[fanout pattern] send：{}", message);
    }

}
