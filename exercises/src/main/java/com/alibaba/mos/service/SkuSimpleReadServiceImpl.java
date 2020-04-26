/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alibaba.mos.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.mos.api.SkuSimpleReadService;
import com.alibaba.mos.data.ChannelInventoryDO;
import com.alibaba.mos.data.SkuDO;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author superchao
 * @version $Id: SkuSimpleReadServiceImpl.java, v 0.1 2019年10月28日 11:54 AM superchao Exp $
 */
@Service
public class SkuSimpleReadServiceImpl implements SkuSimpleReadService {


    @Override
    public List<SkuDO> loadSkus() {
        List<SkuDO> list = new ArrayList<>();
        //TODO 在此实现从resource文件excel中加载sku的代码
        try {
            Workbook workbook = Workbook.getWorkbook(new File("src/main/resources/data/skus.xls"));
            Sheet sheet = workbook.getSheet(0);
            for (int i = 1; i < sheet.getRows(); i++) {
                SkuDO skuDO = new SkuDO();
                List<String> new_list = new ArrayList<>();
                for (int j = 0; j < sheet.getColumns(); j++) {
                    Cell cell = sheet.getCell(j, i);
                    System.out.print(cell.getContents() + "  ");
                    new_list.add(cell.getContents());
                }
                for (int j = 0; j < new_list.size(); j++) {
                    skuDO.setId(new_list.get(0) + "");
                    skuDO.setName(new_list.get(1) + "");
                    skuDO.setArtNo(new_list.get(2) + "");
                    skuDO.setSpuId(new_list.get(3) + "");
                    skuDO.setSkuType(new_list.get(4) + "");
                    Double aDouble = Double.parseDouble(new_list.get(5));
                    skuDO.setPrice((BigDecimal.valueOf(aDouble)));
                    List<ChannelInventoryDO> channelInventoryDOS = JSON.parseArray(new_list.get(6), ChannelInventoryDO.class);


                    skuDO.setInventoryList(channelInventoryDOS);

                }
                list.add(skuDO);
                System.out.println();
            }
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return list;

    }

    @Override
    public Map findSkuId(List<SkuDO> list) {
        Map<String, List<Inventory>> listMap = getSkuAndInventoryList(list);
        HashMap<Object, Object> map = new HashMap<>();
        listMap.forEach((k, v) -> {
            List<Inventory> collect1 = v.stream().sorted(Comparator.comparing(Inventory::getInventory).reversed()).collect(Collectors.toList());
            List<String> stringList = collect1.stream().map(e -> e.getId()).collect(Collectors.toList()).subList(0, 5);
            map.put(k, stringList);
        });
        return map;
    }

    @Override
    public BigDecimal skuPriceSum(List<SkuDO> list) {
        BigDecimal priceSum = BigDecimal.ZERO;
        List<BigDecimal> decimalList = new ArrayList<>();
        list.forEach(p -> {
            BigDecimal bigDecimal = p.getInventoryList().stream()
                    .map(ChannelInventoryDO::getInventory).reduce(BigDecimal.ZERO, BigDecimal::add);
            decimalList.add(bigDecimal.multiply(p.getPrice()));
        });
        priceSum = decimalList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        return priceSum;
    }

    @Override
    public BigDecimal middlePrice(List<SkuDO> list) {
        BigDecimal average =
                list.stream().map(SkuDO::getPrice).
                        reduce(BigDecimal.ZERO, BigDecimal::add)
                        .divide(BigDecimal.valueOf(list.size()), 2,
                                BigDecimal.ROUND_HALF_UP);
        //TODO 未写完
        return null;
    }

    private static SkuDO getApproximate(BigDecimal average, List<SkuDO> list) {
        if (list== null) {
            return null;
        }
        if (list.size() == 1) {
            return list.get(0);
        }
        SkuDO resultSku = null;
        BigDecimal minDifference =  BigDecimal.ZERO;
        BigDecimal minIndex =  BigDecimal.ZERO;
        for (int i = 1; i < list.size(); i++) {
            SkuDO skuDO = list.get(i);
            //TODO 未写完
//            BigDecimal temp = Math.abs(skuDO.getPrice().subtract(average));
//            if (temp < minDifference) {
//                minIndex = i;
//                minDifference = temp;
//            }
        }
        return resultSku;
    }
    private Map<String, List<Inventory>> getSkuAndInventoryList(List<SkuDO> list) {
        Map<String, List<ChannelInventoryDO>> collect = list.stream()
                .collect(Collectors.toMap(SkuDO::getId, SkuDO::getInventoryList));
        Set<Map.Entry<String, List<ChannelInventoryDO>>> entrySet = collect.entrySet();
        Iterator<Map.Entry<String, List<ChannelInventoryDO>>> iterator = entrySet.iterator();
        List<Inventory> inventoryList = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            String skuId = (String) entry.getKey();
            List<ChannelInventoryDO> inventoryDOList = (List<ChannelInventoryDO>) entry.getValue();
            inventoryDOList.forEach(p -> {
                inventoryList.add(Inventory.builder()
                        .id(skuId)
                        .channelCode(p.getChannelCode())
                        .inventory(p.getInventory())
                        .build());
            });
        }
        return inventoryList.stream()
                .collect(Collectors.groupingBy(Inventory::getChannelCode));
    }
}

@Data
@Builder
class Inventory {

    private String id;

    /**
     * 渠道编码, 目前包含: MIAO, TMALL, INTIME 3个渠道
     */
    private String channelCode;
    /**
     * 库存数量, 保留小数点后2位
     */
    private BigDecimal inventory;

    private BigDecimal price;
}