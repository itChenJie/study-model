package com.javaAdvanced;

/**
 * @Author ChenWenJie
 * @Classname HelloGC
 * Describe:
 * @Date 2020/3/2 22:02
 */
public class HelloGC {
    public static void main(String[] args) {
         Long totalMemory=Runtime.getRuntime().totalMemory();
         Long maxMemory=Runtime.getRuntime().maxMemory();
        //初始大小内存，默认为物理内存1/64
        System.out.println("TOTAL_MEMORY(-Xms) = "+totalMemory+"(字节)、"+(totalMemory/1024/1024)+"MB");
        //最大分配内存，默认为物理内存1/4
        System.out.println("MAX_MEMORY(-Xmx) ="+maxMemory+"(字节)、"+(maxMemory/(double)1024/1024)+"MB");
        double a =0.00;
        if(a==0){
            System.out.println("11");
        }



    }
}
