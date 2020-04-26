package com.thread.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author ChenWenJie
 * @Classname OrderTaskServicTest
 * Describe:
 * @Date 2020/4/24 11:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTaskServicTest {
    @Autowired
    private OrderTaskServic taskServic;
    @Test
    public void orderTask() throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    taskServic.orderTask(String.valueOf(finalI));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}