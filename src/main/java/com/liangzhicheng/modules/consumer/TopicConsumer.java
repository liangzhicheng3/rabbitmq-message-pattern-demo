package com.liangzhicheng.modules.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

public class TopicConsumer {

    private static final Logger logger = LoggerFactory.getLogger(TopicConsumer.class);

    @RabbitListener(queues = "#{topicQueue1.name}")
    public void receive1(String message) throws InterruptedException {
        this.receive(message, 1);
    }

    @RabbitListener(queues = "#{topicQueue2.name}")
    public void receive2(String message) throws InterruptedException {
        this.receive(message, 2);
    }

    public void receive(String message, int receiver) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        logger.info("[topic pattern] instance：{} receive：{}", receiver, message);
        this.doWork(message);
        watch.stop();
        logger.info("[topic pattern] instance：{} done {}s", receiver, watch.getTotalTimeSeconds());
    }

    private void doWork(String message) throws InterruptedException {
        for(char ch : message.toCharArray()){
            if(ch == '.'){
                Thread.sleep(1000);
            }
        }
    }

}
