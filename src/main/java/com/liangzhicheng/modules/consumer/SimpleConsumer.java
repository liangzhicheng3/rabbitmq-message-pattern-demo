package com.liangzhicheng.modules.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "simple.hello")
public class SimpleConsumer {

    private static final Logger logger = LoggerFactory.getLogger(SimpleConsumer.class);

    @RabbitHandler
    public void receive(String message){
        logger.info("[simple pattern] receive：{}", message);
    }

}
