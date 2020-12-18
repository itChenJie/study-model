package com.functon接口;

import java.util.function.Consumer;

/**
 * @Author ChenWenJie
 * @Classname ConsumerTest
 * Describe: 练习：
 *      字符串数组当中存有多条信息，请按照格式"姓名:xx。性别:xx。"的格式将信息打印出来。
 *      要求将打印姓名的动作作为第一个Consumer 接口的lambda实例.
 *      将两个Consumer接口按照顺序拼接到一起.
 *
 * @Date 2020/4/11 15:52
 */
public class ConsumerTest {

    public static void printInfo(String[] arr, Consumer<String> con1,Consumer<String> con2){
        for (String s : arr) {
            con1.andThen(con2).accept(s);
        }
    }

    public static void main(String[] args) {
        String[] strings = {"陈,男","华,女","婷,女"};

        printInfo(strings,(s)->{
            String name = s.split(",")[0];
            System.out.print("姓名:"+name);
        },(s)->{
            String sex = s.split(",")[1];
            System.out.println("  年龄:"+sex);
        });


    }
}
