package com.other;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/2/2 下午5:21
 **/
public class AnycaseExercises {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        if (number %2 != 0){
            printGraphics(number);
        }else {
            System.out.println("   不支持！");
        }
    }

    public static void printGraphics(int number){
        BigDecimal bigDecimal = new BigDecimal(2);
        BigDecimal divisor = new BigDecimal(number);
        MathContext mc = new MathContext(1, RoundingMode.HALF_UP);
        double middle =  divisor.divide(bigDecimal, mc).doubleValue();
        String[] graphics = graphics((int) middle, number);
        for (String graphic : graphics) {
            System.out.println(graphic);
        }
    }

    private static String[] graphics(int middle, int number){
        String[] str = new String[number];
        int size = 1;
        int inverted = middle;
        for (int i = 0; i < number; i++) {
            if (i<=middle-1){
                str [i] = rowGraphics(middle,size);
            }else {
                str [i] = rowGraphics(middle,--inverted);
            }
            size++;
        }
        return str;
    }

    private static String rowGraphics(int middle,int size){
        String result = (middle - size) !=0 ? String.format("%1$" + (middle - size) + "s", "") :"";
        for (int i = 0; i < size; i++) {
            result+="*";
        }
        return result;
    }

}
