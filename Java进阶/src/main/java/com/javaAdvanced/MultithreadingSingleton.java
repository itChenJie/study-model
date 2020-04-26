package com.javaAdvanced;

public class MultithreadingSingleton {
    private static MultithreadingSingleton solution = null;

    private MultithreadingSingleton(){
        System.out.println(Thread.currentThread().getName() +"调用默认构造器！");
    }

    //DCL (Doouble Check lock 双端检查机制)
    //直接在方法上加上synchronized 则属于重锁对程序影响很大，
    //在高并发的情况下这种方式是不可取的要做到加锁代码最小话，所以我们使用类同步方式在方法内加锁
    public static MultithreadingSingleton getInstance(){
        if (solution == null){
            synchronized(MultithreadingSingleton.class){
                if (solution == null) {
                    solution = new MultithreadingSingleton();
                }
            }

        }

        return solution;
    }


    public static void main(String[] args) {
        for (int i=1;i<=10;i++){
            new Thread(()->{
                MultithreadingSingleton.getInstance();
            },String.valueOf(i)).start();
        }

    }
}
