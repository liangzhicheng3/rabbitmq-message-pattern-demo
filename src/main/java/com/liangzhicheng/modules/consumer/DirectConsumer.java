package com.liangzhicheng.modules.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

public class DirectConsumer {

    private static final Logger logger = LoggerFactory.getLogger(DirectConsumer.class);

    @RabbitListener(queues = "#{directQueue1.name}")
    public void receive1(String message) throws InterruptedException {
        this.receive(message, 1);
    }

    @RabbitListener(queues = "#{directQueue2.name}")
    public void receive2(String message) throws InterruptedException {
        this.receive(message, 2);
    }

    private void receive(String message, int receiver) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        logger.info("[direct pattern] instance：{} receive：{}", receiver, message);
        this.doWork(message);
        watch.stop();
        logger.info("[direct pattern] instance：{} done {}s", receiver, watch.getTotalTimeSeconds());
    }

    private void doWork(String message) throws InterruptedException {
        for(char ch : message.toCharArray()){
            if(ch == '.') {
                Thread.sleep(1000);
            }
        }
    }

}
