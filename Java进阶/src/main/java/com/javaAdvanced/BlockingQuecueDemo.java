package com.javaAdvanced;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Classname BlockingQuecueDemo
 * @Description TODO
 * @Date 2019/11/16 21:23
 * @Author ChenWenJie
 * ArrayBlockingQueue 是一个基于数组结构的有界阻塞队列，此队列按FIFO（先进先出）原则对元素进行排序。
 * LinkedBlockingQueue 一个基于链表结构的组赛队列。此队列按FIFO(先进先出)排序元素；吞吐量通常要高于ArrayBlockingQueue.
 * SychronousQueue:一个不存储元素的组赛队列，每个插入操作必须等到另一个线程调用移除操作，
 * 否则插入操作一直处于阻塞状态，吞吐量通常要高
 *
 *
 * 1 队列
 *
 * 2阻塞队列
 *
 * 2.1组赛队列有没有好的一面
 *
 * 2.2不得不阻塞，你如何管理
 */
public class BlockingQuecueDemo {


    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("A", 2L, TimeUnit.MICROSECONDS));

        System.out.println(blockingQueue.poll(2L, TimeUnit.MICROSECONDS));
//        System.out.println(blockingQueue.add("a"));
//        System.out.println(blockingQueue.add("b"));
//        System.out.println(blockingQueue.add("c"));
//
//        System.out.println(blockingQueue.remove());
//
//        System.out.println(blockingQueue.element());
    }
}
