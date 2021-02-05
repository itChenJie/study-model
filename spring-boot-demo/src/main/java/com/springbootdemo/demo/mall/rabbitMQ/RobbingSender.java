package com.springbootdemo.demo.mall.rabbitMQ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/2/4 下午3:32
 **/

@Component
public class RobbingSender {
    private static final Logger log = LoggerFactory.getLogger(RobbingSender.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitMQConfig rabbitMQConfig;

    public Boolean sendRobbingMessage(Object msg) {
        UUID correlationDataId = UUID.randomUUID();
        CorrelationData correlationData = new CorrelationData(correlationDataId.toString());
        rabbitTemplate.convertAndSend(rabbitMQConfig.getDirectExchange(), rabbitMQConfig.getRoutingKey(), msg,correlationData);
        return Boolean.TRUE;
    }

}
