package com.provider;

import com.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * @Description 延迟队列
 * @Author ChenWenJie
 * @Data 2020/12/1 8:15 下午
 **/
@Slf4j
@Component
public class DelayProvider implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback{

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    /**
     *  # 发送确认
     *   publisher-returns: true
     * confirm机制确认生产者投递消息到MQ服务器是否成功
     * 生产者投递消息到MQ服务器失败
     *  解决：使用生产者重试机制进行发消息，注意幂等性问题。
     * 幂等性问题
     *  解决：使用
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("消息发送成功" + correlationData);
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
        log.info("发送时间："+ LocalDateTime.now().toString());
        log.info("消息使用的交换器 exchange : " + exchange);
        log.info("消息使用的路由键 routing : " + routingKey);
    }

    public void sendWaitPublished(Map map,String key){
        //id + 时间戳 全局唯一
        CorrelationData correlationData = new CorrelationData("mark_"+key);
        //发送消息时指定  延迟时间
        rabbitTemplate.convertAndSend(RabbitConfig.DELAY_EXCHANGE, RabbitConfig.DELAY_KEY, map,
                new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        //设置消息持久化
                        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                        long delayTime= 3000;
                        message.getMessageProperties().setDelay((int) delayTime);
                        return message;
                    }
                }, correlationData);
    }
}
