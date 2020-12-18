package com.javaAdvanced;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * CAS  ABA问题
 * ABA 问题的解决方式是采用版本号的形式去解决，每次更改数据时
 * 带上当前的版本号，和设置新的版本号，当当前版本号和内存中的版本号不一致时则更改失败并且重新获取内存中的值再进行比较和替换
 */
public class ABADemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<> (100);

    static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {
        System.out.println("=============以下是ABA问题的产生=============");
        new Thread(()->{
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
            System.out.println("t1："+atomicReference.get());
        },"t1").start();

        new Thread(()->{
            //暂停1秒保证t1线程完成一次ABA操作
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2："+atomicReference.compareAndSet(100, 2019)+"\t"+atomicReference.get());
        },"t2").start();

        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("=============以下是解决ABA问题=============");

        new Thread(()->{
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() +"\t第1次版本号"+stamp);
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

            stampedReference.compareAndSet(100,101,stampedReference.getStamp(),stampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() +"\t第2次版本号"+stampedReference.getStamp());
            stampedReference.compareAndSet(101,100,stampedReference.getStamp(),stampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() +"\t第3次版本号"+stampedReference.getStamp());
        },"t3").start();

        new Thread(()->{
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() +"\t第1次版本号"+stamp);
            try { TimeUnit.SECONDS.sleep(4); } catch (InterruptedException e) { e.printStackTrace(); }

            boolean result=stampedReference.compareAndSet(100,2019,stamp,stamp+1);
            System.out.println(Thread.currentThread().getName() + "\t修改是否成功" + result);
            System.out.println(atomicReference.get()+"\t最后一次版本号："+stampedReference.getStamp()+"  而你给的版本号是："+stamp);
        },"t4").start();
    }
}
