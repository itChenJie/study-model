package com.util;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author ChenWenJie
 * @Classname GuavaUtil
 * Describe:
 * @Date 2020/3/19 16:14
 */
public class GuavaUtil {

    public static void main(String[] sges){
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        String time = "2020-03-21";
        try {
            if(sdf.parse(time).getTime()<=new Date().getTime()){
                System.out.println("true");
            }
            System.out.println(sdf.parse(time).getTime()+"  :  "+new Date().getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void ImmutableMap() {
        //按照条件过滤
        ImmutableList<String> names = ImmutableList.of("begin", "code", "Guava", "Java");
        Iterable<String> fitered = Iterables.filter(names, Predicates.or(Predicates.equalTo("Guava"), Predicates.equalTo("Java")));
        System.out.println(fitered);

        //自定义过滤条件   使用自定义回调方法对Map的每个Value进行操作
        ImmutableMap<String, Integer> m = ImmutableMap.of("begin", 12, "code", 15);
        // Function<F, T> F表示apply()方法input的类型，T表示apply()方法返回类型
        Map<String, Integer> m2 = Maps.transformValues(m, new Function<Integer, Integer>() {
            public Integer apply(Integer input) {
                if(input>12){
                    return input;
                }else{
                    return input+1;
                }
            }
        });
        System.out.println(m2);
    }

    private static void Splitter() {
        String str = "1-2-3-4-5-6";
        List<String> list = Splitter.on("-").splitToList(str);
        list.forEach(p-> System.out.println(p));
    }



}
