package com.huawei;

import java.util.Scanner;

/**
 * @Description
 * 有这样一道智力题：“某商店规定：三个空汽水瓶可以换一瓶汽水。小张手上有十个空汽水瓶，她最多可以换多少瓶汽水喝？”答案是5瓶，方法如下：先用9个空瓶子换3瓶汽水，喝掉3瓶满的，喝完以后4个空瓶子，用3个再换一瓶，喝掉这瓶满的，这时候剩2个空瓶子。然后你让老板先借给你一瓶汽水，喝掉这瓶满的，喝完以后用3个空瓶子换一瓶满的还给老板。如果小张手上有n个空汽水瓶，最多可以换多少瓶汽水喝？
 *
 * 输入描述:
 * 输入文件最多包含10组测试数据，每个数据占一行，仅包含一个正整数n（1<=n<=100），表示小张手上的空汽水瓶数。n=0表示输入结束，你的程序不应当处理这一行。
 *
 *
 * 输出描述:
 * 对于每组测试数据，输出一行，表示最多可以喝的汽水瓶数。如果一瓶也喝不到，输出0。
 * @Author ChenWenJie
 * @Data 2021/1/4 下午12:48
 **/
public class QiShuiPing {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextInt()){
            Integer number = scanner.nextInt();
            boolean check = check(number);
            if (check){
                System.out.println("可以喝到："+drinkNumber(number));
            }else {
                return;
            }
        }

    }

    private static boolean check(int number){
        if (number == 0){
            System.out.println("程序结束！");
            return false;
        }
        if (number<1&&number>100){
            System.out.println("仅包含一个正整数n（1<=n<=100");
            return false;
        }
        return true;
    }
    private static   int drinkNumber(int  number){
        int sum = 0;
        while (number>=2){
            if (number == 2){
                number+=1;
            }
            int a = number/3;
            number = number%3+a;
            sum=sum+a;
        }
        return sum;
    }
}
