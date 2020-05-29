package com.springbootdemo.demo.endpoint;

import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Annotation 自定义端点配置类
 * @ClassName DataTimeEndPointConfig
 * @Author ChenWenJie
 * @Data 2020/5/8 10:53 下午
 * @Version 1.0
 **/
@Configuration
public class DataTimeEndPointConfig {
    /**
     * ConditionalOnMissingBean 当这个bean缺少的时候才会去注入这个bean
     *
     * ConditionalOnEnabledEndpoint 当这个监控端点被开启的时候 Enabled才会自定义的去注入到我们的应用程序当中
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnEnabledEndpoint
    public DataTimeEndPoint dataTimeEndPoint() {
       return new DataTimeEndPoint();
    }
}
