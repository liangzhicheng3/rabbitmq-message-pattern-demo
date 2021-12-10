package com.liangzhicheng.config;

import com.liangzhicheng.modules.consumer.SimpleConsumer;
import com.liangzhicheng.modules.provider.SimpleProvider;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleRabbitConfig {

    @Bean
    public Queue simple(){
        return new Queue("simple.hello");
    }

    @Bean
    public SimpleProvider simpleProvider(){
        return new SimpleProvider();
    }

    @Bean
    public SimpleConsumer simpleConsumer(){
        return new SimpleConsumer();
    }

}
