package com.Lambda;


import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @Author ChenWenJie
 * @Classname CartServiceTest
 * Describe:
 * @Date 2020/4/14 14:26
 */
public class CartServiceTest {


    public static void main(String[] args) {
        CartService service = new CartService();
        List<Sku> list = service.filterSkus(service.getCartSkuList(),
                (Sku sku)->sku.getTotalPrice()<2000);
        System.out.println(JSON.toJSONString(list,true));
    }

//    CartService service = new CartService();
//    List<Sku> list = service.filterSkus(service.getCartSkuList(),
//            (Sku sku)->sku.getTotalPrice()<2000);
//        System.out.println(JSON.toJSONString(list,true));

//    CartService service = new CartService();
//    List<Sku> list = service.filterSkus(service.getCartSkuList(), new SkuPredicate() {
//        @Override
//        public boolean test(Sku sku) {
//            return sku.getTotalPrice()<2000;
//        }
//    });
}