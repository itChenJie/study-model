package com.Lambda;

import lombok.Getter;

/**
 * @Author ChenWenJie
 * @Classname SkuCategoryEnum
 * Describe:
 * @Date 2020/4/14 14:03
 */
@Getter
public enum SkuCategoryEnum {
    CLOTHING(10,"服装类"),
    ELECTRONICS(20,"数码类"),
    SPORTS(30,"运动类"),
    BOOKS(40,"图书类");

    private Integer code;
    private String name;

    SkuCategoryEnum(Integer code,String name){
        this.code = code;
        this.name = name;
    }
}
