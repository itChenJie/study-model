package com.javaAdvanced;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Classname ReentrantDemo
 * @Description TODO
 * @Date 2019/11/4 15:31
 * @Author  ChenWenJie
 *
 * 公平和非公平锁
 *
 * 是什么：
 *      公平锁    是指多个线程按照申请锁的顺序来获取锁，类似排队打饭，先来后到
 *
 *      非公平锁   是指多个线程获取锁的顺序并不是按照申请的顺序，有可能后申请的线程比先申请的线程优先获取锁，在高并发的情况下，有可能造成优先级反转或者饥饿现象。
 *
 *
 * 二者的区别
 * 公平/非公平锁
 * 并发包中ReentrantLock 的创建可以指定构造函数的boolean类型来得到公平锁或非公平锁 默认是非公平锁
 * 区别：
 * 公平锁：就是很公平，在并发环境中，每个线程在获取锁时会先查看此锁维护的等待队列，如果为空，或者当前线程是等待队列的第一个，就占有锁，否则就会加入到等待队列中，以后会按照FIFO的规则从队列中取到自己。
 *
 * 非公平锁：比较粗鲁，上来就直接尝试占有额，如果尝试失败，就再采用类似公平锁那种方式。
 *
 * Java ReentrantLock 而言，
 * 通过构造函数指定该锁是否公平锁，默认是非公平锁。非公平锁的优点在于吞吐量比公平锁大。
 *
 * 对于Synchronized而言，也是一种非公平锁
 *
 * 可重入锁（又名递归锁）
 * ReentrantLock /Synchronized 就是一个典型的可重入锁
 *
 * 指的是同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，
 *
 * 同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁。
 *
 * 也就是说，线程可以进入任何一个它已经拥有的锁所同步着的代码块
 *
 * case one Synchronized 就是一个典型的可重入锁
 * t1 invoked sendSMS      t1 线程在外层方法获取锁的时候
 * t1 ##invoked sendEmail  t1 在进入内层方法会自动获取锁
 *
 * t2 invoked sendSMS
 * t2 ##invoked sendEmail
 *
 * case one ReentrantLock 就是一个典型的可重入锁
 * t3  invoked get()
 * t3 ##invoked set()
 * t4  invoked get()
 * t4 ##invoked set()
 *
 *
 * 一个方法里面可以有两个  lock.lock(); lock.unlock();
 * 不会报错 只要有对应的锁和开就不会有问题
 *
 * 如果有lock.lock(); 确没有对应lock.unlock();则会被卡死下面的代码不会再被执行
 *
 */

class Phone implements Runnable {
    public synchronized void  sendSMS(){
        System.out.println(Thread.currentThread().getName()+" invoked sendSMS");
        sendEmail();
    }
    public synchronized  void sendEmail(){
        System.out.println(Thread.currentThread().getName()+" ##invoked sendEmail");
    }

    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    public void get(){
        try{
            lock.lock();
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"  invoked get()");
            set();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
            lock.unlock();
        }
    }

    public  void set(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+" ##invoked set()");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}

public class ReentrantDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            phone.sendSMS();
        },"t1").start();


        new Thread(()->{
           phone.sendSMS();
        },"t2").start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();


        Thread t3 = new Thread(phone ,"t3");
        Thread t4 = new Thread(phone ,"t4");

        t3.start();
        t4.start();
    }


}
