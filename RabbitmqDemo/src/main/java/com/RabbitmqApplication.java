package com;

import com.provider.RabbitmqProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2020/12/1 3:40 下午
 **/
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class RabbitmqApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApplication.class,args);
    }

    @Autowired
    RabbitmqProvider provider;

    @Bean(name = "AAAA")
    public void submit(){
        provider.submit();
    }
}
