package com.liangzhicheng.modules.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import javax.annotation.Resource;

public class SimpleProvider {

    private static final Logger logger = LoggerFactory.getLogger(SimpleProvider.class);

    private static final String queueName = "simple.hello";

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(){
        String message = "Hello World ~";
        rabbitTemplate.convertAndSend(queueName, message);
        logger.info("[simple pattern] send：{}", message);
    }

}
