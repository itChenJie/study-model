package com.javaAdvanced;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author ChenWenJie
 * @Classname SyncAndReentrantLockDemo
 * Describe: 题目：多线程之间按顺序调用，实现A->B->C三个线程启动，要求如下:
 * AA打印5次，BB打印10次，CC打印15次
 * 紧接着
 * AA打印5次，BB打印10次，CC打印15次
 * 来10轮
 * @Date 2020/1/2 21:53
 */
class ShareResource{
    private int number =1; //1：A 2:B 3:C
    private Lock lock = new ReentrantLock();
    //三个
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(){
        lock.lock();
        try {
            //判断
            while (number !=1){
                    c1.await();
            }
            //干活
            for (int i = 1; i <=5 ; i++) {
                System.out.println(Thread.currentThread().getName() +"\t"+i);
            }
            //通知
            number =2;
            //唤醒
            c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


    public void print10(){
        lock.lock();
        try {
            //判断
            while (number !=2){
                c2.await();
            }
            //干活
            for (int i = 1; i <=10 ; i++) {
                System.out.println(Thread.currentThread().getName() +"\t"+i);
            }
            //通知
            number =3;
            //唤醒
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try {
            //判断
            while (number !=3){
                c3.await();
            }
            //干活
            for (int i = 1; i <=15 ; i++) {
                System.out.println(Thread.currentThread().getName() +"\t"+i);
            }
            //通知
            number =1;
            //唤醒
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource resource = new ShareResource();
        new Thread(()->{
            for (int i =1 ; i <=10; i++) {
                resource.print5();
            }
        },"A").start();

        new Thread(()->{
            for (int i =1 ; i <=10; i++) {
                resource.print10();
            }
        },"B").start();

        new Thread(()->{
            for (int i =1 ; i <=10; i++) {
                resource.print15();
            }
        },"C").start();


    }
}
