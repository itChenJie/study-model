package com.jvm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Annotation  algorithm
 * @ClassName ReentrantTest
 * @Author ChenWenJie
 * @Data 2020/5/8 3:12 下午
 * @Version 1.0
 **/
class RenntrantPrint{
    private static Lock lock = new ReentrantLock();
    private static Condition conditionA= lock.newCondition();
    private static Condition conditionB= lock.newCondition();
    private static Condition conditionC= lock.newCondition();
    private static int k=1;

    public void printA(){
        lock.lock();
        try {
            while (k!=1){
                conditionA.await();
            }
            System.out.print("A");
            k=2;
            conditionB.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void printB(){
        lock.lock();
        try {
            while (k!=2){
                conditionB.await();
            }
            System.out.print("B");
            k=3;
            conditionC.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void printC(){
        lock.lock();
        try {
            while (k!=3){
                conditionC.await();
            }
            System.out.println("C");
            k=1;
            conditionA.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class ReentrantTest {

    public static void main(String[] args) {
        RenntrantPrint renntrantPrint = new RenntrantPrint();
        for (int i = 0; i < 10; ++i) {
            new Thread(()->renntrantPrint.printA()).start();
        }

        for (int j = 0; j < 10; j++) {
            new Thread(()->renntrantPrint.printB()).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(()->renntrantPrint.printC()).start();
        }

    }
}
