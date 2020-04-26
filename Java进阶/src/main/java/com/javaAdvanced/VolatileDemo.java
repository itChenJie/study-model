package com.javaAdvanced;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JVM
 * 1.0可见性 volatile 轻量级的同步机制
 * 当代码运行时会先把 number存放在内存中，线程去操作这个变量只能拿到这个变量的复制对象（快照）,
 * 所以会存在当三个线程同时对这个变量进行操作时 1线程改变了变量的值但是，2-3线程并不知道的情况
 * 为了避免这个现象产生java提供了一个关键字 volatile 只要这个变量被修改会在第一时间通知其它线程
 *
 * 2验证 volatile不保证原子性
 *  2.1原子性值的是什么意思？
 *      不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加载分割，需要整体完整要么同时成功，
 *      要么同时失败。
 *
 *  2.2volatile是否可以保证原子性的案例演示 （否）
 *
 * 3 使用 AtomicInteger类型解决原子性的问题
 */
class MyData{
    volatile int number = 0;
    public void addTo60(){
        this.number=60;
    }

    public void addPlusPlus(){
        number++;
    }
    //默认是0 原子性整型
    AtomicInteger  atomicInteger = new AtomicInteger();
    public void addMyAtomic(){
        atomicInteger.getAndIncrement();
    }
}

public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();
            for (int i=1;i<=20;i++){
                new Thread(()->{
                    for (int i1 = 0; i1 < 1000; i1++) {
                        myData.addPlusPlus();
                        myData.addMyAtomic();
                    }
                },String.valueOf(i)).start();
            }
            //需要等待上面的20个线程全部都计算完成后，再用main线程取得最终的结果值看是多少？
            //由于后台默认有二条线程一个main一个GC所以大于2
            while (Thread.activeCount()>2){
                //线程让步
                Thread.yield();
            }
        System.out.println(myData.number);
        System.out.println(myData.atomicInteger);
    }

    //volatile 可以 保存可见性，及时通知其它线程，主物理内存的值已经被修改
    private static void seeOkByVolatile() {
        MyData myData = new MyData();//资源类
        //创建一个线程 对 number变量进行修改
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
                myData.addTo60();
                System.out.println(Thread.currentThread().getName()+"\t updated number value: "+myData.number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"data").start();

        while (myData.number == 0){
            //main线程会一直卡在这，因为它只会在执行时获取一次
            // bnumer变量的值 当data线程对number进行操作它是不可知的
        }
        System.out.println("********"+myData.number);
    }
}
