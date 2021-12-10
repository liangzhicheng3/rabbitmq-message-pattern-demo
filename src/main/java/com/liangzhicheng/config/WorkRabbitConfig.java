package com.liangzhicheng.config;

import com.liangzhicheng.modules.consumer.WorkConsumer;
import com.liangzhicheng.modules.provider.WorkProvider;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkRabbitConfig {

    @Bean
    public Queue work(){
        return new Queue("work.hello");
    }

    @Bean
    public WorkProvider workProvider(){
        return new WorkProvider();
    }

    @Bean
    public WorkConsumer workConsumer1(){
        return new WorkConsumer(1);
    }

    @Bean
    public WorkConsumer workConsumer2(){
        return new WorkConsumer(2);
    }

}
