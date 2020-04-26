package com.javaAdvanced;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Classname ProdConsumer_TraditiomDemo
 * @Description TODO
 * @Date 2019/11/17 16:29
 * @Author ChenWenJie
 * 题目：一个初始化为零的变量，两个线程对其交替操作，一个加1一个减1，来5轮
 * 多线程下判断使用while 不能用if应为有等待和唤醒
 */
class ShareData{
    private int number=0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment()throws Exception{
       lock.lock();
       try {
           while (number!=0){
               //等待不能生产
               condition.await();
           }
           //干活
           number++;
           System.out.println(Thread.currentThread().getName() +"\t"+number);
           //通知唤醒
           condition.signalAll();
       }catch (Exception e){
           e.printStackTrace();
       }finally {
           lock.unlock();
       }

    }

    public void decrement()throws Exception{
        lock.lock();
        try {
            while (number==0){
                //等待不能消费
                condition.await();
            }
            //消费
            number--;
            System.out.println(Thread.currentThread().getName() +"\t"+number);
            //通知唤醒
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}

public class ProdConsumer_TraditiomDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(()->{
            for (int i=1;i<=5;i++){
                try{
                    shareData.increment();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for (int i=1;i<=5;i++){
                try{
                    shareData.decrement();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"BB").start();
    }
}
