package com.springbootdemo.demo;

import com.springbootdemo.demo.productiveConsumption.ProductiveConsumption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * EnableAsync 开启异步支持
 */
@EnableAsync
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    private ProductiveConsumption productiveConsumption;
//    @Bean("ZZ")
//    public void ZZ(){
//        productiveConsumption.execute();
//    }

}
