package main.java.com.Lambda;

import java.util.*;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args) {
        List<Bean> beanList = new ArrayList<Bean>();

        beanList.add(Bean.builder().cardAmount(40).status(1).type(1).build());
        beanList.add(Bean.builder().cardAmount(40).status(1).type(1).build());
        beanList.add(Bean.builder().cardAmount(40).status(1).type(1).build());
        beanList.add(Bean.builder().cardAmount(50).status(1).type(1).build());
        beanList.add(Bean.builder().cardAmount(50).status(1).type(1).build());
        beanList.add(Bean.builder().cardAmount(60).status(2).type(1).build());
        beanList.add(Bean.builder().cardAmount(50).status(2).type(1).build());
        beanList.add(Bean.builder().cardAmount(50).status(2).type(1).build());
        beanList.add(Bean.builder().cardAmount(60).status(2).type(1).build());
        beanList.add(Bean.builder().cardAmount(50).status(2).type(1).build());

        System.out.println("Original...");

        List<Map.Entry<Integer, Map<Integer, Long>>> collect3 = beanList.stream()
                .collect(Collectors.groupingBy(Bean::getStatus, Collectors.groupingBy(Bean::getCardAmount, Collectors.counting()))).entrySet().stream().collect(Collectors.toList());

        System.out.println(collect3.toString());
    }
}
