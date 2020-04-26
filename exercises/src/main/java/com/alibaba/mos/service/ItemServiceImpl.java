/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alibaba.mos.service;

import com.alibaba.mos.api.ItemService;
import com.alibaba.mos.data.ItemDO;
import com.alibaba.mos.data.SkuDO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author superchao
 * @version $Id: ItemServiceImpl.java, v 0.1 2019年10月28日 11:11 AM superchao Exp $
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Override
    public List<ItemDO> aggregationSkus(List<SkuDO> skuList) {
        //TODO 在此实现聚合sku的代码
//        对于sku type为原始商品(ORIGIN)的, 按货号(artNo)聚合成ITEM
//            对于sku type为数字化商品(DIGITAL)的, 按spuId聚合成ITEM
//              聚合结果需要包含: item的最大价格、最小价格、sku列表及全渠道总库存

        return null;
    }
}