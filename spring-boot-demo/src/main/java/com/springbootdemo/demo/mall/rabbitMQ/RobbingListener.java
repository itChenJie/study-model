package com.springbootdemo.demo.mall.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.springbootdemo.demo.mall.service.ProductService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/2/4 下午3:08
 **/
//@Slf4j
//@Component
//@RabbitListener(queues = {"${defineProps.rabbit.direct.queue}"})
//public class RobbingListener {
//    @Autowired
//    private ProductService productService;
//
//    @SneakyThrows
//    @RabbitHandler
//    public void receiver(@Payload Map<String,Object> msgMap, @Headers Channel channel, Message message){
//        log.info("用户：{}开始抢单,商品code：{}", msgMap.get("userId"),msgMap.get("productCode"));
//        try {
//            productService.robbingProduct((String) msgMap.get("productCode"), (Integer) msgMap.get("userId"));
//            // 确认消息已经消费成功
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        } catch (IOException e) {
//            e.printStackTrace();
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
//        }
//    }
//}
