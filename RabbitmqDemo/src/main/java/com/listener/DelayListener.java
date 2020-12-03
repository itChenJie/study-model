package com.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @Description 延迟队列消费
 * @Author ChenWenJie
 * @Data 2020/12/1 8:22 下午
 **/
@Slf4j
@Component
public class DelayListener  {


    @RabbitListener(queues = "delay")
    public void listener(Map map, Channel channel, Message message) throws IOException {
        try {
            // 模拟执行任务
            Thread.sleep(1000);
            log.info("--jxb--DelayListener--listener：" + map);
            log.info("消费时间："+ LocalDateTime.now().toString());
            // 确认收到消息，false只确认当前consumer一个消息收到，true确认所有consumer获得的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                log.info("消息已重复处理失败,拒绝再次接收！");
                // 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列则进入死信队列
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                log.info("消息即将再次返回队列处理！");
                // requeue为是否重新回到队列，true重新入队
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
            e.printStackTrace();
        }
    }

}
