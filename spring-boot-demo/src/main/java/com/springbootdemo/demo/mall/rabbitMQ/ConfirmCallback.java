package com.springbootdemo.demo.mall.rabbitMQ;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/2/5 下午1:52
 **/
@Slf4j
public class ConfirmCallback implements RabbitTemplate.ConfirmCallback{
    /**
     * 消息发送到交换器Exchange后触发回调。
     * 使用该功能需要开启确认，spring-boot中配置如下：
     * publisher-confirm-type: correlated
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("消息已确认 cause:{} - {}", cause, correlationData);
        } else {
            log.error("消息未确认 cause:{} - {}", cause, correlationData);
        }
    }
}
