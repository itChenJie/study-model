package com.javaAdvanced;

import java.util.concurrent.CountDownLatch;

/**
 * 让一些线程阻塞直到另一个线程完成一系列操作后才被唤醒
 * CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，
 * 调用线程会被阻塞。其它线程调用countDown方法会将计算器减1（调用CountDown方法的线程不会阻塞）,
 * 当计数器的值变为零时，因调用await方法被阻塞的线程会被唤醒，继续执行。
 * @Classname CountDownLatchDemo
 * @Description TODO
 * @Date 2019/11/10 20:16
 * @Author ChenWenJie
 */
public class CountDownLatchDemo {

    /**
     * 实际业务场景是要循环中的所有线程都执行完毕，才能执行下面的代码
     * @param args
     */
    public static void main(String[] args) {
        //值为6
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i=1;i<=6;i++) {

                System.out.println(Thread.currentThread().getName() + "\t国，被灭");
            new Thread(()->{
                //没循环一次减一
                countDownLatch.countDown();
            }, CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();
        }
        //当为0的时候main 线程才能继续走下去
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t***************秦国，一统华夏");
    }
}
