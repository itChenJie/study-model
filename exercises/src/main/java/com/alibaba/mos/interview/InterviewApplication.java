package com.alibaba.mos.interview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author superchao
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class},scanBasePackages = {"com.alibaba.mos"})
@ImportResource({"classpath*:application-bean.xml"})
public class InterviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterviewApplication.class, args);
    }

}
