package com.Lambda;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ChenWenJie
 * @Classname Sku
 * Describe:
 * @Date 2020/4/14 13:58
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sku {

    private Integer skuId;

    private String skuName;

    private Double skuPrice;

    private Integer totalNum;

    private Double totalPrice;

    private Enum skuCategory;
}
