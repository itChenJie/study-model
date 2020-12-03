package com.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

import java.io.IOException;
import java.util.Map;

/**
 * @Description 接口
 * @Author ChenWenJie
 * @Data 2020/12/3 11:25 上午
 */
public interface Listener {

    public void listener(Map map, Channel channel, Message message) throws IOException ;
}
