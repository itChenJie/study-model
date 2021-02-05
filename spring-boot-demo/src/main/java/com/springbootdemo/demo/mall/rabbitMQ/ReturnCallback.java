package com.springbootdemo.demo.mall.rabbitMQ;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/2/5 下午1:53
 **/
@Slf4j
public class ReturnCallback implements RabbitTemplate.ReturnCallback {
    /**
     * 通过实现ReturnCallback接口，
     * 如果消息从交换器发送到对应队列失败时触发
     * 比如根据发送消息时指定的routingKey找不到队列时会触发
     * 使用该功能需要开启确认，spring-boot中配置如下：
     * spring.rabbitmq.publisher-returns = true
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("消息被退回-> {}",message);
        log.error("消息使用的交换机:{}", exchange);
        log.error("消息使用的路由键:{}", routingKey);
        log.error("描述:{}", replyText);
    }
}
