/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alibaba.mos.api;

import com.alibaba.mos.data.SkuDO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author superchao
 * @version $Id: SkuReadService.java, v 0.1 2019年10月28日 10:45 AM superchao Exp $
 */
public interface SkuSimpleReadService {

    /**
     * 从excel读取sku数据
     * @return
     */
    List<SkuDO> loadSkus();

    Map findSkuId(List<SkuDO> list);

    BigDecimal skuPriceSum(List<SkuDO> list);

    BigDecimal middlePrice(List<SkuDO> list);
}