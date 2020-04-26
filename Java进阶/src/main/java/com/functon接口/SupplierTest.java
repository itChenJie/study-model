package com.functon接口;

import java.util.function.Supplier;

/**
 * @Author ChenWenJie
 * @Classname SupplierTest
 * Describe:
 * java.util.function.Supplier<T> 接口仅包含一个无参的方法:T get() .用来获取一个泛型参数的指定的对象数据。
 *
 * Supplier<T> 接口被称之为生产型接口，指定接口的泛型是什么类型，那么接口中的get方法就会生产什么类型的数据
 * @Date 2020/4/11 15:35
 */
public class SupplierTest {


    public static void main(String[] args) {
        int[] numbers = {100, 200, 300, 400, 500, -600, -700, -800, -900, -1000};
        int numberMax = arrayMax(
                () -> {
                    int max = numbers[0];
                    for (int number : numbers) {
                        if (max < number) {
                            max = number;
                        }
                    }
                    return max;
                }
        );
        System.out.println("数组中的最大值为：" + numberMax);
    }


    private static int arrayMax(Supplier<Integer> supplier) {
        return supplier.get();
    }
}
