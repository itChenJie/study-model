package com.springbootdemo.demo.mall.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/2/3 下午4:49
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;

    private String productCode;

    private Integer total;
}
