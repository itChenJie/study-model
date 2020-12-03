package com.provider;

import com.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 提供消息
 * @Author ChenWenJie
 * @Data 2020/12/1 3:49 下午
 **/
@Slf4j
@Component
public class RabbitmqProvider implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }
    /**
     * 提供消息
     */
    public void submit(){
        Map map = new HashMap();
        map.put("code",1002);
        map.put("name","陈杰");
        rabbitTemplate.convertAndSend(RabbitConfig.DEMO_EXCHANGE_NAME,RabbitConfig.DEMO_KEY,map);
    }

    /**
     *  # 发送确认
     *   publisher-returns: true
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("消息发送成功" + correlationData);
            log.info("cause："+ cause);
        } else {
            log.info("消息发送失败:" + cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        // 反序列化对象输出
        log.info("消息主体: " + SerializationUtils.deserialize(message.getBody()));
        log.info("应答码: " + replyCode);
        log.info("描述：" + replyText);
        log.info("消息使用的交换器 exchange : " + exchange);
        log.info("消息使用的路由键 routing : " + routingKey);
    }
}
