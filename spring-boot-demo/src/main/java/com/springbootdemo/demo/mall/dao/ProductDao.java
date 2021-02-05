package com.springbootdemo.demo.mall.dao;

import com.springbootdemo.demo.mall.bean.Product;
import com.springbootdemo.demo.mall.bean.ProductRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description 接口
 * @Author ChenWenJie
 * @Data 2021/2/3 下午5:29
 */
@Repository
@Mapper
public interface ProductDao {

    /**
     * 插入抢单记录
     *
     * @param record 抢单记录实体
     * @return 成功返回1，失败返回0
     */
    int insertProductRecord(ProductRecord record);

    /**
     * 更新商品表，把商品表的库存减少1
     * @param productCode 商品编号
     * @return 成功返回1，失败返回0
     */
    int updateProduct(@Param("productCode") String productCode);

    /**
     * 根据商品编号寻找商品
     * @param productCode 商品编号
     * @return
     */
    Product selectProductByNo(@Param("productCode") String productCode);
}
