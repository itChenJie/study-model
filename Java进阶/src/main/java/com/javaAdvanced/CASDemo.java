package main.java.com.javaAdvanced;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS
 * 比较并交换
 *
 * 如果线程中的期望值和主物理内存的真实值不一致则不允许被修改，并且重新获取主物理内存中的值
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(5);

        System.out.println(i.compareAndSet(5, 2019)+"\t update"+i.get());
        System.out.println(i.compareAndSet(5, 2014)+"\t update"+i.get());
    }
}
