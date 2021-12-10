package com.liangzhicheng.config;

import com.liangzhicheng.modules.consumer.DirectConsumer;
import com.liangzhicheng.modules.provider.DirectProvider;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitConfig {

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("exchange.direct");
    }

    @Bean
    public Queue directQueue1() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue directQueue2() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding directBinding1a(DirectExchange directExchange, Queue directQueue1) {
        return BindingBuilder.bind(directQueue1).to(directExchange).with("orange");
    }

    @Bean
    public Binding directBinding1b(DirectExchange directExchange, Queue directQueue1) {
        return BindingBuilder.bind(directQueue1).to(directExchange).with("black");
    }

    @Bean
    public Binding directBinding2a(DirectExchange directExchange, Queue directQueue2) {
        return BindingBuilder.bind(directQueue2).to(directExchange).with("green");
    }

    @Bean
    public Binding directBinding2b(DirectExchange directExchange, Queue directQueue2) {
        return BindingBuilder.bind(directQueue2).to(directExchange).with("black");
    }

    @Bean
    public DirectProvider directProvider(){
        return new DirectProvider();
    }

    @Bean
    public DirectConsumer directConsumer(){
        return new DirectConsumer();
    }

}
