package com.javaAdvanced;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Classname SynchronousQueueDemo
 * @Description TODO
 * @Date 2019/11/17 15:30
 * @Author ChenWenJie
 * 阻塞队列
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName() +"\t put 1");
                blockingQueue.put("1");

                System.out.println(Thread.currentThread().getName() +"\t put 2");
                blockingQueue.put("2");

                System.out.println(Thread.currentThread().getName() +"\t put 3");
                blockingQueue.put("3");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();


        new Thread(()->{
            try {

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"BBB").start();
    }
}
