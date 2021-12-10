package com.liangzhicheng.modules.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import javax.annotation.Resource;

public class WorkProvider {

    private static final Logger logger = LoggerFactory.getLogger(WorkProvider.class);

    private static final String queueName = "work.hello";

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(int index){
        StringBuffer buffer = new StringBuffer("Hello");
        int limitIndex = index % 3 + 1;
        for(int i = 0; i < limitIndex; i++){
            buffer.append(".");
        }
        buffer.append(index + 1);
        String message = buffer.toString();
        rabbitTemplate.convertAndSend(queueName, message);
        logger.info("[work pattern] send：{}", message);
    }

}
