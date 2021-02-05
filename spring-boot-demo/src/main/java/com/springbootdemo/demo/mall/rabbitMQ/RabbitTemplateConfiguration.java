package com.springbootdemo.demo.mall.rabbitMQ;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/2/5 下午1:50
 **/
@Configuration
public class RabbitTemplateConfiguration {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setReturnCallback(new ReturnCallback());
        rabbitTemplate.setRecoveryCallback((var1) -> {
            System.out.println("重试回调:");
            String messageStr = new ObjectMapper().writeValueAsString(var1);
            System.out.println(messageStr);
            return var1;
        });
        rabbitTemplate.setConfirmCallback(new ConfirmCallback());
        return rabbitTemplate;
    }
}
