package com.springbootdemo.demo.mall.service;

import com.springbootdemo.demo.mall.bean.Product;
import com.springbootdemo.demo.mall.bean.ProductRecord;
import com.springbootdemo.demo.mall.dao.ProductDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/2/3 下午4:56
 **/
@Slf4j
@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    public void robbingProduct(String productCode,Integer userId){
        Product productByNo = productDao.selectProductByNo(productCode);
        if (productByNo !=null && productByNo.getTotal() > 0) {
            int updateProduct = productDao.updateProduct(productCode);
            if (updateProduct > 0){
                productDao.insertProductRecord(
                        ProductRecord.builder().productCode(productCode).userId(userId).build());
                log.info("用户{}抢单成功", userId);
            }else {
                log.info("用户{}抢单失败", userId);
            }
        }else {
            log.info("用户{}抢单失败", userId);
        }
    }
}
