package com.functon接口;

import java.util.function.Function;

/**
 * @Author ChenWenJie
 * @Classname FunctionTest
 * Describe:自定义函数模型拼接
 *          题目
 *          使用Function进行函数模型的拼接，按照顺序需要执行的多个函数操作为：
 *           str = "陈杰,22";
 *           分析:
 *           1.将字符串数字年龄部分，得到字符串;
 *               Function<String, String> "陈杰,22" -> "20"
 *           2.将上一步的字符串转换成为int类型的数字;
 *               Function<String, Integer> “20” -> 20
 *           3.将上一步的int数字累加100，得到结果int 数字。
 *              Function<Integer, Integer> 20 -> 120
 * @Date 2020/4/10 17:40
 */
public class FunctionTest {

    public static int change(String s, Function<String, String> fun1,
                             Function<String, Integer> fun2, Function<Integer, Integer> fun3) {
        return fun1.andThen(fun2).andThen(fun3).apply(s);
    }

    public static void main(String[] args) {
        String str = "陈杰,22";
        int num = change(str, (String s) -> {
            return s.split(",")[1];
        }, (String s) -> {
            return Integer.parseInt(s);
        }, (Integer i) -> {
            return i + 100;
        });
        System.out.println(num);
    }
}
