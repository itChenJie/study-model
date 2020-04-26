package com.javaAdvanced;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author ChenWenJie
 * @Classname ProdConsumer_BlockQueueDemo
 * Describe: volatile/CAS/atomicInteger/BlockQueueDemo/线程交互原子引用
 * 线程通信之生产者消费者阻塞队列版
 * @Date 2020/1/4 14:45
 */
class MyResource {
    private volatile boolean FLAG=true;
    private AtomicInteger counter = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;
    //提高适配性，尽量传接口不传类
    public MyResource(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }
    //生产
    public void myProd() throws InterruptedException {
        String data="";
        boolean retValue;
        while (FLAG){
            data =counter.incrementAndGet()+"";
            retValue = blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if(retValue){
                System.out.println(Thread.currentThread().getName() +"\t 插入队列"+data+"成功！");
            }else{
                System.out.println(Thread.currentThread().getName() +"\t 插入队列"+data+"失败！");
            }
            //暂停是为了让消费操作被执行完成，实际情况中我们在乎的是生产是否会被多次消费，从而造成生产数和消费数不对应的问题，使用阻塞队列则必须一进一出
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() +"\t 被叫停了，表示FLAG==flase，生产结束");
    }

    //消费
    public void myConsumer()throws InterruptedException {
        String result=null;
        while (FLAG){
            result=blockingQueue.poll(2L,TimeUnit.SECONDS);
            //当服务器性能太好时可能下面的业务操作还没完成生产线程就又启动了
            if (result == null || result.equalsIgnoreCase("")){
                System.out.println(Thread.currentThread().getName() +"\t 超过两秒没有获取到产品，消费退出！");
                System.out.println("\n\n");
                FLAG = false;
                return;
            }
            System.out.println(Thread.currentThread().getName() +"\t 消费队列"+result+"消费成功!");
        }
    }

    public void stop(){
        this.FLAG = false;
    }
}

public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) {
        //队列的容量
        MyResource res = new MyResource(new ArrayBlockingQueue<>(1));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 生产线程启动");
            try {
                res.myProd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Prod").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 消费线程启动");
            try {
                res.myConsumer();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Consumer").start();

        //暂停一会
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n\n\n");
        System.out.println("6秒钟时间到，停止生产，活动结束");
        res.stop();
    }
}
