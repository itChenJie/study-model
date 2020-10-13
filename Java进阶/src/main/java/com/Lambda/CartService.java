package main.java.com.Lambda;

import java.util.ArrayList;
import java.util.List;
import main.java.com.Lambda.Sku;
import  main.java.com.Lambda.SkuCategoryEnum;
/**
 * @Author ChenWenJie
 * @Classname CartService
 * Describe:
 * @Date 2020/4/14 14:02
 */
public class CartService {

    public static List<Sku> cartSkuList = new ArrayList<Sku>(){
        {
            add(new Sku(6213,"无人机",4999.00,1,4900.00,SkuCategoryEnum.ELECTRONICS));
            add(new Sku(6214,"VR一体机",2999.00,1,2900.00, SkuCategoryEnum.ELECTRONICS));
            add(new Sku(6215,"衬衣",199.00,1,199.00, SkuCategoryEnum.CLOTHING));
            add(new Sku(6216,"短袖",99.00,1,99.00, SkuCategoryEnum.CLOTHING));
            add(new Sku(6217,"跑鞋",599.00,1,599.00, SkuCategoryEnum.SPORTS));
            add(new Sku(6218,"护腕",69.00,1,69.00, SkuCategoryEnum.SPORTS));
            add(new Sku(6219,"jvm解析",59.00,1,59.00, SkuCategoryEnum.BOOKS));
            add(new Sku(6220,"SpringBoot介绍",79.00,1,79.00, SkuCategoryEnum.BOOKS));
        }
    };

    public List<Sku> getCartSkuList(){
        return cartSkuList;
    }

    /**
     * 查询出电子产品
     * @param cartSkuList
     * @return
     */
    public List<Sku> filterElectronicsSkus(List<Sku> cartSkuList){
        List<Sku> list = new ArrayList<>();
        for (Sku sku : cartSkuList) {
            if(SkuCategoryEnum.ELECTRONICS.equals(sku.getSkuCategory())){
                list.add(sku);
            }
        }
        return list;
    }

    /**
     *
     * @param cartSkuList
     * @param categoryEnum
     * @return
     */
    public List<Sku> filterSkusCategory(List<Sku> cartSkuList, SkuCategoryEnum categoryEnum){
        List<Sku> list = new ArrayList<>();
        for (Sku sku : cartSkuList) {
            if(categoryEnum.equals(sku.getSkuCategory())){
                list.add(sku);
            }
        }
        return list;
    }

    /**
     * Version 4.0.0
     * 根据不同的Sku判断标准，对sku列表进行过滤
     * @param cartSkuList
     * @param predicate 不同的Sku判断标准策略
     * @return
     */
    public List<Sku> filterSkus(List<Sku> cartSkuList, SkuPredicate predicate){
        ArrayList<Sku> result = new ArrayList<>();
        for (Sku sku : cartSkuList) {
            if(predicate.test(sku)){
                result.add(sku);
            }
        }
        return result;
    }
}
