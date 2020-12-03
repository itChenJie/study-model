package com.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2020/12/1 3:49 下午
 **/
@Component
public class RabbitmqListener implements Listener{
    /**
     * acknowledge-mode: manual
     * 采用手动提交ack方式，当因抛出异常导致消费失败时，采用重试机制，
     * 但也不能一直重试否则容易导致服务器挂掉，所以配置中使用max-attempts控制最大重试次数
     * 为了保证最终的一致性要求，当消费次数达到最大重试次数时，根据使用场景的不同做出不同的处理机制
     * 1：采用回调机制通知提供者服务进行事物的回滚。保证数据的最终一致性
     * 2：入消费失败库进行存档，方便人工进入进行手动处理
     * @param map
     * @param channel
     * @param message
     * @throws IOException
     */
    @RabbitListener(queues = "demo")
    @Override
    public void listener(Map map, Channel channel, Message message) throws IOException {
        try {
            // 模拟执行任务
            Thread.sleep(1000);
            System.out.println("--jxb--RabbitmqListener--listener：" + map);
            // 确认收到消息，false只确认当前consumer一个消息收到，true确认所有consumer获得的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                System.out.println("消息已重复处理失败,拒绝再次接收！");
                // 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列则进入死信队列
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                System.out.println("消息即将再次返回队列处理！");
                // requeue为是否重新回到队列，true重新入队
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
            e.printStackTrace();
        }
    }


}
