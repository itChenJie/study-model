package com.thread.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ChenWenJie
 * @Classname OrderTaskServic
 * Describe:
 * @Date 2020/4/24 11:18
 */
@Service
public class OrderTaskServic {
    @Autowired
    private ThreadTaskService taskService;

    public void orderTask() throws InterruptedException {
        this.cancelOrder();
        taskService.sendMessage();
        taskService.sendMessage2();
    }
    // 取消订单
    private void cancelOrder() {
        System.out.println("取消订单的方法执行------开始");
        System.out.println("取消订单的方法执行------结束 ");
    }
}
