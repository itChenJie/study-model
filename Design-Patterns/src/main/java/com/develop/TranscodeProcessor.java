package com.develop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/7/14 11:45 上午
 **/
@Slf4j
public class TranscodeProcessor extends AbstractDistributionProcessor{
    @Override
    public void process(DistributeRequest request) {
        log.info("处理第三方转码");
        this.postProcess("1",true,request);
    }

    @Override
    public void postProcess(String id, boolean processResult,DistributeRequest request) {
        log.info("处理第三方转码通知******");
        nextProcessor.process(request);
    }
}
