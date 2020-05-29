package com.huawei;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Annotation 手写Map的几种遍历方式
 * @ClassName MapTraverse
 * @Author ChenWenJie
 * @Data 2020/5/29 3:56 下午
 * @Version 1.0
 **/
public class MapTraverse {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap();
        map.put("a","1");
        map.put("b","2");
        map.put("c","3");
        map.put("d","4");
        one(map);
        System.out.println();
        two(map);
        System.out.println();
        three(map);
        System.out.println();
        four(map);
    }

    private static void four(Map<String, String> map) {
        for (String value : map.values()) {
            System.out.print(value+"  ");
        }
    }

    private static void three(Map<String, String> map) {
        for(Map.Entry<String, String> entry: map.entrySet()){
            System.out.println("key："+entry.getKey()+" value: "+entry.getValue());
        }
    }

    private static void two(Map<String, String> map) {
        for (Object o : map.keySet()) {
            System.out.print(map.get(o)+" ");
        }
    }

    private static void one(Map<String, String> map) {
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.print(entry.getValue()+"   ");
        }
    }
}
