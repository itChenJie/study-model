package com.huawei;

import java.util.Scanner;

/**
 * @Description
 * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
 *
 *
 * 输入描述:
 * 输入一个十六进制的数值字符串。注意：一个用例会同时有多组输入数据，请参考帖子https://www.nowcoder.com/discuss/276处理多组输入的问题。
 * @Author ChenWenJie
 * @Data 2021/1/4 下午9:59
 **/
public class BaseConversion {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()){
            String str=sc.next();
            System.out.println(Integer.decode(str));
        }
    }
}
