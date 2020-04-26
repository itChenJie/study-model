package com.javaAdvanced;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程的控制。
 * @Classname SemaphoreDemo
 * @Description TODO
 * @Date 2019/11/11 21:47
 * @Author ChenWenJie
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        //三个车位（控制每一次的并发线程数）
        Semaphore semaphore = new Semaphore(3);
        //6辆车，(总数)
        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                try {
                    //获得
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() +"\t抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() +"\t停车3秒后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //释放
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
