package main.java.com.Lambda;

import main.java.com.Lambda.Sku;

/**
 * @Author ChenWenJie
 * @Classname SkuBooksCategoryPredicate
 * Describe: 对sku商品类型为图书类的判断标准
 * @Date 2020/4/14 15:37
 */
public class SkuBooksCategoryPredicate implements SkuPredicate {
    @Override
    public boolean test(Sku sku) {
        return SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory());
    }


}
