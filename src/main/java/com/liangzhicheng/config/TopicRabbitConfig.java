package com.liangzhicheng.config;

import com.liangzhicheng.modules.consumer.TopicConsumer;
import com.liangzhicheng.modules.provider.TopicProvider;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig {

    @Bean
    public TopicExchange topic() {
        return new TopicExchange("exchange.topic");
    }

    @Bean
    public Queue topicQueue1() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue topicQueue2() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding topicBinding1a(TopicExchange topicExchange, Queue topicQueue1) {
        return BindingBuilder.bind(topicQueue1).to(topicExchange).with("*.orange.*");
    }

    @Bean
    public Binding topicBinding1b(TopicExchange topicExchange, Queue topicQueue1) {
        return BindingBuilder.bind(topicQueue1).to(topicExchange).with("*.*.rabbit");
    }

    @Bean
    public Binding topicBinding2a(TopicExchange topicExchange, Queue topicQueue2) {
        return BindingBuilder.bind(topicQueue2).to(topicExchange).with("lazy.#");
    }

    @Bean
    public TopicProvider topicProvider() {
        return new TopicProvider();
    }

    @Bean
    public TopicConsumer topicConsumer() {
        return new TopicConsumer();
    }

}
