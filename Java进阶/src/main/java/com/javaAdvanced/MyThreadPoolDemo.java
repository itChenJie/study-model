package com.javaAdvanced;

import java.util.concurrent.*;

/**
 * @Author ChenWenJie
 * @Classname MyThreadPoolDemo
 * Describe: 第4种获得使用java多线程的方式，线程池
 * 1、继承Thread类，本质上也是实现Runnable接口 2、Runnable接口 无返回值，不抛异常
 * 3、Callable接口 支持泛型返回，抛异常 4、线程池
 * 线程池4个常用方式
 * 线程池：用来控制运行的线程的数量，处理过程中将任务放入等待队列，然后在线程创建后启动这些任务。
 * 如果线程数量超过了最大数量(maximumPoolSize)，超出数量的线程将会在等待队列排队等候。如果等待队列已满，
 * 再进来的任务就会按照拒绝策略拒绝。
 *
 * 线程池的特点：线程复用，控制最大并发数，管理线程。
 *
 * 线程池的优势：
 *
 * 1.降低资源消耗，通过复用线程来降低创建和销毁线程的消耗。
 *
 * 2.提高响应速度，当任务到达时不需要等待创建线程。
 *
 * 3.提高线程的可管理性，使用线程池可以进行统一的分配，监控和调优。
 *
 * 生产中使用线程池不允许使用Executors创建而是必须要通过ThreadPoolExecutor的方式去创建，
 * 因为队列给的是默认长度而线程的默认长度是Integer.MAX_VALUE太大了
 * @Date 2020/1/5 14:19
 */
//底层都是使用的ThreadPoolExecutor的7个参数

/**
 * corePoolSize：核心池的大小，这个参数跟后面讲述的线程池的实现原理有非常大的关系。
 * 在创建了线程池后，默认情况下，线程池中并没有任何线程，而是等待有任务到来才创建线程去执行任务，
 * 除非调用了prestartAllCoreThreads()或者prestartCoreThread()方法，从这2个方法的名字就可以看出，
 * 是预创建线程的意思，即在没有任务到来之前就创建corePoolSize个线程或者一个线程。默认情况下，
 * 在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，
 * 当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中；
 *
 * maximumPoolSize：线程池最大线程数，这个参数也是一个非常重要的参数，它表示在线程池中最多能创建多少个线程；
 *
 * keepAliveTime：表示线程没有任务执行时最多保持多久时间会终止。默认情况下，只有当线程池中的线程数大于corePoolSize时，
 * keepAliveTime才会起作用，直到线程池中的线程数不大于corePoolSize，即当线程池中的线程数小于corePoolSize时，
 * 如果一个线程空闲的时间达到keepAliveTime，则会终止，直到线程池中的线程数不超过corePoolSize。
 * 但是如果调用了allowCoreThreadTimeOut(boolean)方法，在线程池中的线程数不大于corePoolSize时，
 * keepAliveTime参数也会起作用，直到线程池中的线程数为0；
 *
 * unit：参数keepAliveTime的时间单位，有7种取值：
 * TimeUnit.DAYS;//天
 * TimeUnit.HOURS;//小时
 * TimeUnit.MINUTES;//分钟
 * TimeUnit.SECONDS;//秒
 * TimeUnit.MILLISECONDS;//毫秒
 * TimeUnit.MICROSECONDS;//微妙
 * TimeUnit.NANOSECONDS;//纳秒
 *
 * workQueue：一个阻塞队列，用来存储等待执行的任务，这个参数的选择也很重要，会对线程池的运行过程产生重大影响，一般来说，
 * 这里的阻塞队列有以下几种选择：ArrayBlockingQueue和PriorityBlockingQueue使用较少，一般使用LinkedBlockingQueue和Synchronous。
 * 线程池的排队策略与BlockingQueue有关。
 * threadFactory：线程工厂，主要用来创建线程；
 *
 * handler：表示当拒绝处理任务时的策略，有以下四种取值：
 * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
 * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
 * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
 * ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //一池5个处理线程
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //一池一个处理线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //一池N个处理线程
        //ExecutorService threadPool = Executors.newCachedThreadPool();
        //Java8新特性
        //ExecutorService threadPool3 = Executors.newWorkStealingPool();

        /**
         * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
         * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
         * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
         * ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
         */
        ExecutorService threadPool = new ThreadPoolExecutor(2,5,1L,
                TimeUnit.SECONDS,new LinkedBlockingQueue<>(3),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        try{
            //能承受的最大线程数是最大线程数+队列长度
            //模拟N个用户来办理业务，每个用户就是一个来自外部的请求线程
            for (int i = 1; i <=8; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() +"\t 办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
