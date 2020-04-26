package com.rabbitMQ.util;

import com.rabbitMQ.config.RebbitmqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author ChenWenJie
 * @Classname Producer
 * Describe:
 * @Date 2020/4/1 15:26
 */
public class Producer {
    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendEmail(String message){
        /**
         * 交互机名称
         * routingKey
         * 消息内容
         */
        rabbitTemplate.convertAndSend(RebbitmqConfig.EXCHANGE_TOPICS_INFORM,"inform.email",message);
    }
}
