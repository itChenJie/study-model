package com.develop;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/7/14 11:35 上午
 **/
public abstract class AbstractDistributionProcessor {

    protected AbstractDistributionProcessor nextProcessor;

    public AbstractDistributionProcessor setNextProcessor(AbstractDistributionProcessor nextProcessor ){
        this.nextProcessor = nextProcessor;
        return this.nextProcessor;
    }

    public AbstractDistributionProcessor getNextProcessor(){
        return this.nextProcessor;
    }

    public abstract void process(DistributeRequest request);

    /**
     * 处理之后的通知
     * @param id
     * @param processResult
     */
    public abstract void postProcess(String id, boolean processResult,DistributeRequest request);
}
