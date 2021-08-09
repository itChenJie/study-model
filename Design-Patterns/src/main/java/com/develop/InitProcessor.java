package com.develop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/7/14 11:41 上午
 **/
@Slf4j
public class InitProcessor extends AbstractDistributionProcessor{
    @Override
    public void process(DistributeRequest request) {
      log.info("记录一条分发任务：{}",request.toString());
      nextProcessor.process(request);
    }

    @Override
    public void postProcess(String id, boolean processResult,DistributeRequest request) {

    }
}
