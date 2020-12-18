package com.functon接口;

        import java.util.function.Predicate;

/**
 * @Author ChenWenJie
 * @Classname PredicateTest
 * Describe: Predicate<T> 接口
 *           作用:对某中数据类型的数据进行判断，结果返回一个boolea值
 *
 *           Predicate接口中包含一个抽象方法;
 *           boolean test(T t)用来指定数据类型数据进行判断的方法
 *           结果:
 *              符号条件,返回true
 *              不符合条件，返回false
 * @Date 2020/4/11 16:22
 */
public class  PredicateTest {
    public static boolean checkString(String s, Predicate<String> predicate){
        return predicate.test(s);
    }

    public static void main(String[] args) {
        String str ="123456";
        boolean b = checkString(str, s -> {
            return str.length() > 5;
        });
        System.out.println(b);
    }
}
