package com.springbootdemo.demo.mall.service.impl;

import com.springbootdemo.demo.mall.rabbitMQ.RobbingSender;
import com.springbootdemo.demo.mall.service.InitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/2/3 下午5:53
 **/
@Slf4j
@Service
public class InitServiceImpl implements InitService {
    private int threadNum = 3;

    private AtomicInteger userId = new AtomicInteger(0);

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Autowired
    private RobbingSender robbingSender;

    @Override
    public void generateMultiThread() {
//        ExecutorService pool = Executors.newFixedThreadPool(threadNum);
//        for (int i = 0; i < threadNum; i++) {
//            pool.execute(new RobbingTask(countDownLatch));
//        }
//        countDownLatch.countDown();
        for (int i = 0; i < 5; i++) {
            Map<Object, Object> map = new HashMap<>();
            map.put("productCode","Code12313");
            map.put("userId",userId.getAndIncrement());
            robbingSender.sendRobbingMessage(map);
        }
    }

    class RobbingTask implements Runnable{
        private CountDownLatch countDownLatch;

        RobbingTask(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
          try {
              /**
               * 阻塞住当前线程
               * 等待主线程提示开抢，主线程调用countDownLatch.countDown();后
               * 开始执行下面的productService.robbingProduct(userId);
               */
              countDownLatch.await();
              Map<Object, Object> map = new HashMap<>();
              map.put("productCode","Code12313");
              map.put("userId",userId.getAndIncrement());
              robbingSender.sendRobbingMessage(map);
          }catch (Exception e){
              log.error("an exception was occurred , caused by :{}", e.getMessage());
          }
        }
    }
}
