package com.huawei;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @Annotation  数值枚举
 * @Author ChenWenJie
 * @Data 2020/5/25 6:58 下午
 */
@Getter
@AllArgsConstructor
public enum ArrayEnum {
    TWO("2","5"),
    FIVE("5","2"),
    SIX("6","9"),
    NINE("9","6");
    /**
     * 描述
     */
    private String description;
    /**
     * 编码
     */
    private String code;

    public static String of(String code) {
        for (ArrayEnum value : values()) {
            if (value.description.equals(code)) {
                return value.code;
            }
        }
        return code;
    }
}
