package com.liangzhicheng.modules.controller;

import com.liangzhicheng.modules.provider.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/rabbitmq")
public class RabbitMQController {

    @Resource
    private SimpleProvider simpleProvider;
    @Resource
    private WorkProvider workProvider;
    @Resource
    private FanoutProvider fanoutProvider;
    @Resource
    private DirectProvider directProvider;
    @Resource
    private TopicProvider topicProvider;

    /**
     * 简单模式，它包含一个生产者、一个消费者和一个队列，
     * 生产者向队列里发送消息，消费者从队列中获取消息并消费
     */
    @GetMapping(value = "/simple")
    public void testSimple() throws InterruptedException {
        for(int i = 0; i < 10; i++){
            simpleProvider.send();
            Thread.sleep(1000);
        }
    }

    /**
     * 工作模式，指向多个互相竞争的消费者发送消息的模式，它包含一个生产者、两个消费者和一个队列，
     * 两个消费者同时绑定到一个队列上去，当消费者获取消息处理耗时任务时，空闲的消费者从队列中获取并消费消息
     */
    @GetMapping(value = "/work")
    public void testWork() throws InterruptedException {
        for(int i = 0; i < 10; i++){
            workProvider.send(i);
            Thread.sleep(1000);
        }
    }

    /**
     * 发布/订阅模式，指同时向多个消费者发送消息的模式（类似广播的形式），它包含一个生产者、两个消费者、两个队列和一个交换机，
     * 两个消费者同时绑定到不同的队列上去，两个队列绑定到交换机上去，生产者通过发送消息到交换机，所有消费者接收并消费消息
     */
    @GetMapping(value = "/fanout")
    public void testFanout() throws InterruptedException {
        for(int i = 0; i < 10; i++){
            fanoutProvider.send(i);
            Thread.sleep(1000);
        }
    }

    /**
     * 路由模式，可以根据路由键选择性给多个消费者发送消息的模式，它包含一个生产者、两个消费者、两个队列和一个交换机，
     * 两个消费者同时绑定到不同的队列上去，两个队列通过路由键绑定到交换机上去，生产者发送消息到交换机，
     * 交换机通过路由键转发到不同队列，队列绑定的消费者接收并消费消息
     */
    @GetMapping(value = "/direct")
    public void testDirect() throws InterruptedException {
        for(int i = 0; i < 10; i++){
            directProvider.send(i);
            Thread.sleep(1000);
        }
    }

    /**
     * 通配符模式，可以根据路由键匹配规则选择性给多个消费者发送消息的模式，它包含一个生产者、两个消费者、两个队列和一个交换机，
     * 两个消费者同时绑定到不同的队列上去，两个队列通过路由键匹配规则绑定到交换机上去，生产者发送消息到交换机，
     * 交换机通过路由键匹配规则转发到不同队列，队列绑定的消费者接收并消费消息
     */
    @GetMapping(value = "/topic")
    public void testTopic() throws InterruptedException {
        for(int i = 0; i < 10; i++){
            topicProvider.send(i);
            Thread.sleep(1000);
        }
    }

}
