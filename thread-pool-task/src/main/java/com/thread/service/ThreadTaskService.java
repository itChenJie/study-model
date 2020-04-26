package com.thread.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @Author ChenWenJie
 * @Classname ThreadTaskService
 * Describe:两个异步方法
 * 第一个类（这里模拟取消订单后发短信，有两个发送短信的方法）：
 * 第二个类。调用发短信的方法 （异步方法不能与被调用的异步方法在同一个类中，否则无效）：
 * @Date 2020/4/24 11:14
 */
@Service
public class ThreadTaskService  {
    Logger log = LoggerFactory.getLogger(ThreadTaskService.class);
    //@Async("taskExecutor") 对应我们自定义线程池中的 @Bean("taskExecutor") ，表示使用我们自定义的线程池。
    @PostConstruct
    @Async("taskExecutor")
    public void sendMessage() throws InterruptedException {
        log.info("发送短信方法----   执行开始");
        Thread.sleep(5000); // 模拟耗时
        log.info("发送短信方法----   执行结束");
    }

    @PostConstruct
    @Async("taskExecutor")
    public void sendMessage2() throws InterruptedException {
        log.info("发送短信方法----    执行开始");
        Thread.sleep(5000); // 模拟耗时
        log.info("发送短信方法----    执行结束");
    }
}
