package com.zerenlian.dynamicliabilitychain;

/**
 * @Description 接口
 * @Author ChenWenJie
 * @Data 2020/12/8 1:50 下午
 */
public interface Pipeline  {
    Pipeline fireTaskReceived();

    Pipeline fireTaskFiltered();

    Pipeline fireTaskExecuted();

    Pipeline fireAfterCompletion();
}
