package com.liangzhicheng.modules.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@RabbitListener(queues = "work.hello")
public class WorkConsumer {

    private static final Logger logger = LoggerFactory.getLogger(WorkConsumer.class);

    private final int instance;

    public WorkConsumer(int i){
        this.instance = i;
    }

    @RabbitHandler
    public void receive(String message) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        logger.info("[work pattern] instance：{} receive：{}", this.instance, message);
        this.doWork(message);
        watch.stop();
        logger.info("[work pattern] instance：{} done {}s", this.instance, watch.getTotalTimeSeconds());
    }

    private void doWork(String message) throws InterruptedException {
        for(char ch : message.toCharArray()){
            if(ch == '.'){
                Thread.sleep(1000);
            }
        }
    }


}
