package com.rabbitMQ.util;

import com.rabbitMQ.config.RebbitmqConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author ChenWenJie
 * @Classname ProducerTest
 * Describe:
 * @Date 2020/4/1 15:30
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ProducerTest {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Test
    public void sendEmail() {
        String message=" test data 002";
        /**
         * 交互机名称
         * routingKey
         * 消息内容
         */
        rabbitTemplate.convertAndSend(RebbitmqConfig.EXCHANGE_TOPICS_INFORM,"inform.email",message);
    }
}