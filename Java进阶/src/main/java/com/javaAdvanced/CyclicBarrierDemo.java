package com.javaAdvanced;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier的字面意思是可循环(Cyclic)使用的屏障（Barrier）。它要做的事情是，
 * 让一组线程到达一个屏障(也可以叫同步点)时被阻塞，直到最后一个线程到达屏障时，
 * 屏障才会开门，所有被屏障拦截的线程才会继续干活，线程进入屏障通过CyclicBarrier的await方法。
 * @Classname CydicBarrierDemo
 * @Description TODO
 * @Date 2019/11/11 21:31
 * @Author ChenWenJie
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        //当CyclicBarrier中的值为7的时候才会被执行
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{System.out.println("****开始开会");});

        for (int i = 1; i <=7 ; i++) {
            int finalI = i;
            new Thread(()->{
                System.out.println(finalI +"员工："+Thread.currentThread().getName() +"到了");
                try {
                    //进行阻塞
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

    }
}
