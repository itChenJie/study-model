package com.zerenlian.simple;

/**
 * @Description 任务处理接口
 * @Author ChenWenJie
 * @Data 2020/12/8 7:37 下午
 */
public interface Handler {
    /**
     * 任务处理
     * @param object
     * @return
     */
    Object task(Object object);
}
