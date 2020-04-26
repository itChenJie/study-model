package com.Lambda;

/**
 * @Author ChenWenJie
 * @Classname SkuTotalPricedicate
 * Describe:对Sku的总价是否超过2000作为判断标准
 * @Date 2020/4/14 15:40
 */
public class SkuTotalPricedicate implements SkuPredicate {
    @Override
    public boolean test(Sku sku) {
        return sku.getTotalPrice()>2000;
    }
}
