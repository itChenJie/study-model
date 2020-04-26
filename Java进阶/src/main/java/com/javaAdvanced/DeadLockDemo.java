package com.javaAdvanced;

import java.util.concurrent.TimeUnit;

/**
 * @Author ChenWenJie
 * @Classname DeadLockDemo
 * Describe: 死锁是指两个或两个以上的进程在执行过程中，
 * 因争夺资源而造成的一种互斥等待的现象，
 * 若无外力干涉那它们都将无法推进下去
 * @Date 2020/1/5 21:38
 */
class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() +"\t 自己持有："+lockA
                    +"\t尝试获得："+lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() +"\t 自己持有："+lockB
                        +"\t尝试获得："+lockA);
            }
        }
    }


}

public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA ="lockA";
        String lockB ="lockB";

        new Thread(new HoldLockThread(lockA,lockB),"ThreadAAAA").start();
        new Thread(new HoldLockThread(lockB,lockA),"ThreadBBBB").start();
        /**
         * linux ps -ef|grep xxxx ls -l
         * window下的java运行程序 也有类似ps的查看进程命令，但是目前我们需要
         * 查看的只是java
         *   jps =java ps         jps -l 线程
         *   jstack 进程号 具体线程
         */
    }
}
