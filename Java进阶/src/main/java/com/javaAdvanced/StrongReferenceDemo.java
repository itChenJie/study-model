package com.javaAdvanced;

/**
 * @Author ChenWenJie
 * @Classname StrongReferenceDemo
 * Describe:
 * @Date 2020/3/8 15:35
 */
public class StrongReferenceDemo {

    public static void main(String[] args) {
        Object obj1 = new Object();//这样定义的默认就是强引用
        Object obj2 = obj1;//obj2引用赋值
        System.gc();
        System.out.println(obj2);


    }
}
