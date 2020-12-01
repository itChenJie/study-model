package com.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description RabbitMQ 配置类
 * @Author ChenWenJie
 * @Data 2020/12/1 3:51 下午
 **/
@Configuration
public class RabbitConfig {
    /**
     * 交换机名称
     */
    public static final String DEMO_EXCHANGE_NAME ="demoExchange";
    /**
     * key
     */
    public static final String DEMO_KEY = "demoKey";
    /**
     * 队列名称
     */
    private static final String DEMO = "demo";
    /**
     * 构建交护机
     */
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(DEMO_EXCHANGE_NAME,true,true);
    }

    /**
     * 构建系列化
     */
    @Bean
    public Queue demoQueue(){
        return new Queue(DEMO,true);
    }
    /**
     * 绑定交护机
     */
    @Bean
    public Binding demoBinding(){
        return BindingBuilder.bind(demoQueue()).to(directExchange()).with(DEMO_KEY);
    }
}
