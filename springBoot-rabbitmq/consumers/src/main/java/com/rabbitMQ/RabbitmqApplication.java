package com.rabbitMQ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author ChenWenJie
 * @Classname RabbitmqApplication
 * Describe:
 * @Date 2020/4/1 14:35
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class RabbitmqApplication {
    public static void main(String[] args){
        SpringApplication.run(RabbitmqApplication.class);
    }
}
