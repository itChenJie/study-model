package com.stock;

/**
 * @Description 算法之DP——买卖股票
 * @Author ChenWenJie
 * @Data 2020/10/30 5:07 下午
 **/
public class BusinessStock {

    /**
     * 1. 买卖股票的最佳时机
     *
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），
     * 设计一个算法来计算你所能获取的最大利润。注意：你不能在买入股票前卖出股票。
     */
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        //初始化为数组首元素
        int min = prices[0];
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            //如果当前卖出
            max = Math.max(max, prices[i] - min);
            //更新最小值
            min = Math.min(min, prices[i]);
        }
        return max;
    }



}
