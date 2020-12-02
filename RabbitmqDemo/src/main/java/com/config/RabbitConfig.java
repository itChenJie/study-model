package com.config;

import org.springframework.amqp.core.*;
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
    public static final String DELAY_EXCHANGE = "delayExchange";

    /**
     * key
     */
    public static final String DEMO_KEY = "demoKey";
    public static final String DELAY_KEY = "delayKey";

    /**
     * 队列名称
     */
    private static final String DEMO = "demo";
    private static final String DELAY = "delay";

    /**
     * 队列延迟时间
     */
    public final static int QUEUE_DELAY = 4000;

    /**
     * 构建交护机
     */
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(DEMO_EXCHANGE_NAME,true,true);
    }

    /**
     * 构建延迟队列
     */
    @Bean
    public TopicExchange topicExchange(){ TopicExchange exchange = new TopicExchange(DELAY_EXCHANGE, true, false);
        exchange.setDelayed(true);
        return exchange;
    }

    /**
     * 构建系列化
     */
    @Bean
    public Queue demoQueue(){
        return new Queue(DEMO,true);
    }

    /**
     * 构建延迟系列化
     */
    @Bean
    public Queue delayQueue() {
        // 支持持久化
        return new Queue(DELAY, true);
    }

    /**
     * 绑定交护机
     */
    @Bean
    public Binding demoBinding(){
        return BindingBuilder.bind(demoQueue()).to(directExchange()).with(DEMO_KEY);
    }

    /**
     * 绑定延迟交换机和
     *
     * @return
     */
    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(delayQueue()).to(topicExchange()).with(DELAY_KEY);
    }
}
