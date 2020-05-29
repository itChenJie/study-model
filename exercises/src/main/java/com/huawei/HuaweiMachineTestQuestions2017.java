package com.huawei;

import java.util.*;

/**
 * @Annotation
 * 1.编写一个reverseadd函数，实现两个数倒置后再求和的功能，比如输入123，
 * 456就是求321+654，输出975。注意：输入100,200输出3,（自动去除开头的0）,超过范围输出-1
 * 思路：接收两个数字，如果为负数，先打印负号，再转为正数进行处理。倒序就利用取余。
 * 如果余数为零，则跳过该次循环，不为零，则存到字符串。注意：调整语句要放到continue语句前，否则会死循环。
 * @ClassName RepeatCharacter
 * @Author ChenWenJie 华为机试题
 * @Data 2020/5/26 6:38 下午
 * @Version 1.0
 **/
public class HuaweiMachineTestQuestions2017 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int nextInt1 = scan.nextInt();
        int nextInt2 = scan.nextInt();
        reverseadd(nextInt1,nextInt2);


    }

    private static void reverseadd(int nextInt1, int nextInt2) {
        if(nextInt1<1||nextInt2<1){
            
        }
    }


}
