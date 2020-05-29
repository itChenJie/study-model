package com.huawei;

import org.apache.commons.lang3.StringUtils;
import org.thymeleaf.util.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Annotation 题目描述：
 * 小明负责公司年会，想出一个趣味游戏：屏幕给出1~9中任意3个不重复的数字，大家以最快时间给出这几个数字可拼成的数字从小到大排列位于第N位置的数字，其中N为给出的数字中最大的（如果不到这么多个数字则给出最后一个即可），谁最快给出谁得奖。
 * <p>
 * 注意：
 * （1）屏幕如果给出的是“2”，大家可把它当作“2”，也可把它当作“5”来拼接数字；同理，如果屏幕给的是“5”，大家可把它当作“5”，也可以把它当作“2”来拼接数字，但屏幕不能同时给出“2”和“5”。
 * （2）屏幕如果给出的是“6”，大家可把它当作“6”，也可把它当作“9”来拼接数字；同理，如果屏幕给的是“9”，大家可把它当作“9”，也可以把它当作“6”来拼接数字，但屏幕不能同时给出“6”和“9”。
 * <p>
 * 现在需要编写一个小程序，根据给出的数字计算出能组合的所有2数字以及最终的正确答案。
 * <p>
 * 如：给出：1，4，8，则可以拼成的数字为：
 * 1，4，8，14，18，41，48，81，84，148，184，418，481，814，841
 * <p>
 * 那么最第N（即8）个的数字为81.
 * <p>
 * 输入描述：以逗号为分隔，描述3个int类型整数的字符串。
 * <p>
 * 输出描述：这几个数字可拼成的数字从小到大排列位于第N（N为输入数字中最大的数字）位置的数字，如果输入的数字为负数或者不是合法的字符串或者有重复，返回-1。
 * <p>
 * 输入例子：1，4，8
 * <p>
 * 输出例子：81
 * @ClassName Arrangement
 * @Author ChenWenJie
 * @Data 2020/5/25 3:36 下午
 * @Version 1.0
 **/
public class Arrangement {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine().trim();
        List repeat = isRepeat(line);
        //获取可以拼成的数字
        Object max = digitalSplice(repeat, line);
        System.out.println("那么最第N个的数字为:" + max);
    }

    /**
     * 判断输入的三个数字是否合法
     * 是否存在 2-5；6-9
     *
     * @param line
     * @return
     */
    private static List isRepeat(String line) {
        String[] split = line.split(",");
        if (ArrayUtils.isEmpty(split) || split.length != 3) {
            throw new RuntimeException("输入的数字存在异常 例：1,2,3!");
        }
        isDuplicate(split);
        regex(line);
        ArrayList<Integer> result = new ArrayList<>();
        Arrays.stream(split).forEach(s -> {
            digitalJudgment(s);
            //2<->5;6<->9
            if (ArrayEnum.of(s).equals(s)) {
                result.add(Integer.valueOf(s));
            } else {
                result.add(Integer.valueOf(s));
                result.add(Integer.valueOf(ArrayEnum.of(s)));
            }

        });
        return result;
    }


    /**
     * 这几个数字可拼成的数字从小到大排列位
     *
     * @param list
     */
    private static Object digitalSplice(List<Integer> list, String line) {
        list = list.stream().sorted().collect(Collectors.toList());
        Integer max = list.get(list.size() - 1);
        List<Integer> newList = new ArrayList<>();
        newList.addAll(list);
        for (int i = 0; i < list.size(); i++) {
            for (int i1 = 0; i1 < list.size(); i1++) {
                Integer integer = list.get(i);
                Integer integer1 = list.get(i1);
                if (!integer.equals(integer1)) {
                    newList.add(integer * 10 + integer1);
                }
            }
        }
        System.out.println("给出：" + line + "则可以拼成的数字为:" + newList);
        return newList.get(max - 1);
    }


    /**
     * 屏幕不能同时给出“2”和“5”
     * 屏幕不能同时给出“6”和“9”
     *
     * @param line
     */
    private static void regex(String line) {
        String regex = ".*2.*5";
        if (line.matches(regex)) {
            throw new RuntimeException("屏幕不能同时给出“2”和“5”!");
        }
        regex = ".*6.*9";
        if (line.matches(regex)) {
            throw new RuntimeException("屏幕不能同时给出“6”和“9”!");
        }
    }

    /**
     * 输入的数字存在重复
     *
     * @param split
     */
    private static void isDuplicate(String[] split) {
        long count = Arrays.stream(split).distinct().count();
        if (split.length != count) {
            throw new RuntimeException("输入的数字存在重复 例：1,2,3!");
        }
    }

    private static void digitalJudgment(String s) {
        if (s.equals("")) {
            throw new RuntimeException("输入的数字不能为空 例：1,2,3!");
        }else if(!StringUtils.isNumeric(s)){
            throw new RuntimeException("请输入整数!");
        } else if (Integer.parseInt(s) > 9) {
            throw new RuntimeException("输入的数字不能大于9!");
        }
    }

}
