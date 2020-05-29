package com.springbootdemo.demo.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @Annotation jackson配置类
 * @ClassName JacksonConfig
 * @Author ChenWenJie
 * @Data 2020/5/8 9:28 下午
 * @Version 1.0
 **/
@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper getObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        //为空的属性不对json系列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //对所有时间类型系列化时都按照 yyyy-MM-dd HH:mm进行格式化
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm"));

        return objectMapper;
    }
}
