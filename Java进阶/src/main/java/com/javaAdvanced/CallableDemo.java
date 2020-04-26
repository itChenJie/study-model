package com.javaAdvanced;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author ChenWenJie
 * @Classname CallableDemo
 * Describe:
 * @Date 2020/1/4 17:58
 */
class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("****************come in Callable");
        return 1024;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //Thread 类本身是不能直接使用Callable接口的，只能给Runnable，所以为了让他认识要找一个中间类
        //一个 futureTask对象只能被一个线程操作如果要多个则再创建一个
        //  FutureTask<Integer> futureTask2 = new FutureTask<Integer> (new MyThread());
        FutureTask<Integer> futureTask = new FutureTask<Integer> (new MyThread());
        Thread t1 = new Thread(futureTask,"aaa");
        t1.start();
        //这个线程池的主要好处在用，当 MyThread资源类要花费大量时间时而为了不影响main线程的正常使用我们单独给它开一个线程出来，
        // 最后在main线程需要使用MyThread的结果说通过  futureTask.get()获取
        //当MyThread线程为完成操作时使用futureTask.get()会把main线程卡死(阻塞)
        //而怎么知道它是否已经操作结束了呢?
        //true结束，false未结束
        while (futureTask.isDone()){

        }

        //获取 MyThread.call的返回结果
        System.out.println("*****result:"+futureTask.get());
    }
}
