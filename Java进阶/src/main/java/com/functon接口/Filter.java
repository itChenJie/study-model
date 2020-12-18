package com.functon接口;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @Author ChenWenJie
 * @Classname Filter
 * Describe:
 * @Date 2020/4/11 17:05
 */
public class Filter {
    private static String getString(Supplier<String> supplier) {
        return supplier.get();
    }

    public static void main(String[] args) {
        List<String[]> list = new ArrayList<>();
        String[] s ={"陈,男","华,女","婷,女"};
        list.add(s);
        String[] s1 ={"陈,男","华,女","婷,女"};
        list.add(s1);
        String[] s2 ={"陈,男","华,女","婷,女"};
        list.add(s2);
        List<String> list1 = new ArrayList<>();
        for (String[] strings1 : list) {
            String[] nameArr = getString(()->{
                return StringUtils.join(Arrays.asList(strings1),";");
              }).split(";");
            Arrays.stream(nameArr).map(p -> list1.add(p));
        }
        List<String> str5 = list1.stream().filter(str -> {
            return str.contains("女");
        }).collect(Collectors.toList());
        str5.forEach(z-> System.out.println(z.toString()));
    }
}
