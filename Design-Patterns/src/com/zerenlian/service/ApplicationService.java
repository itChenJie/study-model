package com.zerenlian.service;

import com.zerenlian.dynamicliabilitychain.DefaultPipeline;
import com.zerenlian.dynamicliabilitychain.Pipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2020/12/8 7:06 下午
 **/
@Service
public class ApplicationService {
    @Autowired
    private ApplicationContext context;

    public void mockedClient(){
        Object request = new Object();
        Pipeline pipeline = newPipeline(request);
        try {
            pipeline.fireTaskReceived();
            pipeline.fireTaskFiltered();
            pipeline.fireTaskExecuted();
        } finally {
            pipeline.fireAfterCompletion();
        }

    }

    private Pipeline newPipeline(Object request) {
        return context.getBean(DefaultPipeline.class, request);
    }
}
