package com.jvm;

import java.util.concurrent.Semaphore;

/**
 * @Annotation
 * @ClassName SemaphoreTest
 * @Author ChenWenJie
 * @Data 2020/5/8 10:22 上午
 * @Version 1.0
 **/

public class SemaphoreTest {

    public static void main(String[] args) {
        print print = new print();
        new Thread(()->{print.printA();}).start();
        new Thread(()->{print.printB();}).start();
        new Thread(()->{print.printC();}).start();
    }
}

class print {
    //只允许 1个线程同时访问
    private Semaphore semaphoreA = new Semaphore(1);
    //不允许线程直接访问需要通过唤醒才能执行
    private Semaphore semaphoreB = new Semaphore(0);
    private Semaphore semaphoreC = new Semaphore(0);


    public void printA(){
        execit("A",semaphoreA,semaphoreB);
    }

    public void printB(){
        execit("B",semaphoreB,semaphoreC);
    }

    public void printC(){
        execit("C",semaphoreC,semaphoreA);
    }

    public void execit(String val,Semaphore currentSemaphore, Semaphore nextSemaphore){
        for (int i = 0; i < 10;) {
            try {
                currentSemaphore.acquire();
                System.out.print(val);
                if ("C".equals(val)){
                    System.out.println();
                }
                i++;
                //释放
                nextSemaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}