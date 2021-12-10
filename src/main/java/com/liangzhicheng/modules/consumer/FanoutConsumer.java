package com.liangzhicheng.modules.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@RabbitListener(queues = "exchange.fanout")
public class FanoutConsumer {

    private static final Logger logger = LoggerFactory.getLogger(FanoutConsumer.class);

    @RabbitListener(queues = "#{fanoutQueue1.name}")
    public void receive1(String message) throws InterruptedException {
        this.receive(message, 1);
    }

    @RabbitListener(queues = "#{fanoutQueue2.name}")
    public void receive2(String message) throws InterruptedException {
        this.receive(message, 2);
    }

    private void receive(String message, int receiver) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        logger.info("[fanout pattern] instance：{} receive：{}", receiver, message);
        this.doWork(message);
        watch.stop();
        logger.info("[fanout pattern] instance：{} done {}s", receiver, watch.getTotalTimeSeconds());
    }

    private void doWork(String message) throws InterruptedException {
        for(char ch : message.toCharArray()){
            if(ch == '.'){
                Thread.sleep(1000);
            }
        }
    }

}
