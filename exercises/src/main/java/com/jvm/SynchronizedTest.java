package com.jvm;

import java.nio.file.Path;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Annotation
 * @ClassName LockTest
 * @Author chenjie
 * @Data 2020/5/7 6:08 下午
 * @Version 1.0
 **/
class Print {


    public void print1(){
        System.out.print("A");
    }

    public void print2(){
        System.out.print("B");
    }

    public void print3(){
        System.out.println("C");
    }
}

public class SynchronizedTest {


    private static int k = 1;

    public static void main(String[] args) {
        final Print print = new Print();
        new Thread(()->{
            synchronized (print) {
                for (int i = 0; i < 10; i++) {
                    while (k!=1){
                        try {
                            print.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    print.print1();
                    k=2;
                    print.notifyAll();
                }
            }
        }).start();

        new Thread(()->{
            synchronized (print) {
                for (int i = 0; i < 10; i++) {
                    while (k != 2) {
                        try {
                            print.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    print.print2();
                    k = 3;
                    print.notifyAll();
                }
            }
        }).start();

        new Thread(()->{
            synchronized (print) {
                for (int i = 0; i < 10; i++) {
                    while (k != 3) {
                        try {
                            print.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    print.print3();
                    k = 1;
                    print.notifyAll();
                }
            }
        }).start();
    }
}
