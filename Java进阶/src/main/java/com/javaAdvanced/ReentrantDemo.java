package com.javaAdvanced;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Classname ReentrantDemo
 * @Description TODO
 * @Date 2019/11/4 15:31
 * @Author  ChenWenJie
 *
 * 可重入锁（又名递归锁）
 * ReentrantLock /Synchronized 就是一个典型的可重入锁
 *
 * 指的是同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，
 * 在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁。
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
        lock.lock();
        lock.lock();
        try{
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
