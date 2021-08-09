package com.develop;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/7/14 2:56 下午
 **/
@Slf4j
public class DistributeProcessor extends AbstractDistributionProcessor{
    @Override
    public void process(DistributeRequest request) {
        log.info("处理第三方分发");
        this.postProcess("1",true,request);
    }

    @Override
    public void postProcess(String id, boolean processResult, DistributeRequest request) {
        log.info("处理第三方分发通知******");
//        nextProcessor.process(request);
    }
}
