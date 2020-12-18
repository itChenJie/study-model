package com.zerenlian;

import com.zerenlian.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.FileNotFoundException;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2020/12/10 1:48 下午
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Autowired
    ApplicationService service;

    @Bean("ZZZ")
    public void test() throws FileNotFoundException {
        service.mockedClient();
    }
}
