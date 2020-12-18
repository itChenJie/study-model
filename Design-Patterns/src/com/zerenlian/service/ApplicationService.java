package com.zerenlian.service;

import com.zerenlian.dynamicliabilitychain.DefaultPipeline;
import com.zerenlian.dynamicliabilitychain.Pipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2020/12/8 7:06 下午
 **/
@Service
public class ApplicationService {
    @Autowired
    private ApplicationContext context;

    public void mockedClient() throws FileNotFoundException {
        File file = new File("/Users/chenjie/Desktop/Idea/study-model/人员导入.xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        Pipeline pipeline = newPipeline(inputStream);
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
