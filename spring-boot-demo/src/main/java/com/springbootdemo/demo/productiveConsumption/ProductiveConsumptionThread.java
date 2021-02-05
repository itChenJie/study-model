package com.springbootdemo.demo.productiveConsumption;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author ChenWenJie 程序
 * @Data 2021/2/3 下午3:45
 **/
@Slf4j
@Component
public class ProductiveConsumptionThread {

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void produce() {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            log.info(Thread.currentThread().getName()+": 生产成功");
            number++;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void consumption(){
        lock.lock();
        try {
            while (number == 0){
                condition.await();
            }
            number--;
            log.info(Thread.currentThread().getName()+": 消费成功");
            condition.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
