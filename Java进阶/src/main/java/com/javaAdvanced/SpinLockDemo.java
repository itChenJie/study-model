package main.java.com.javaAdvanced;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Classname SpinLockDemo
 * @Description TODO
 * @Date 2019/11/9 20:43
 * @Author ChenWenJie
 *
 * 自旋锁(spinlock)
 *
 * 是值尝试获取锁的线程不会立即阻塞，而是采用循环的方式去尝试获取锁，
 * 这样的好处是减少线程上下文切换的消耗，缺点是循环会消耗CPU
 *    public final int getAndAddInt(Object var1, long var2, int var4) {
 *         int var5;
 *         do {
 *             var5 = this.getIntVolatile(var1, var2);
 *         } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
 *
 *         return var5;
 *     }
 *
 *
 * 题目：实现一个自旋锁
 *  自旋锁好处：循环比较获取直到成功为止，没有类似wait的阻塞。
 *
 *  通过CAS操作完成自旋锁，A线程先进来调用myLock方法自己持有锁5秒，
 *  B随后进来发现当前有程序有锁，不是null，所以只能通过自旋等待，
 *  直到A释放锁后B随后抢到。
 */
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t come in");

        while (!atomicReference.compareAndSet(null,thread)){
            System.out.println(Thread.currentThread().getName()+"\t atomicReference:"+atomicReference.get());
        }
    }

    public void myUnLock(){
        Thread thread = Thread.currentThread();
        //如果当前atomicReference中的线程等于当前线程，则把他赋值成null
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"\t incoked myUnLock ");
    }
    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() ->{
            spinLockDemo.myLock();
            try{
                TimeUnit.SECONDS.sleep(5);
            }catch (Exception e){
                e.printStackTrace();
            }

            spinLockDemo.myUnLock();
        },"AA").start();



        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            e.printStackTrace();
        }




        new Thread(() ->{
            spinLockDemo.myLock();
            spinLockDemo.myUnLock();
        },"BB").start();
    }
}
