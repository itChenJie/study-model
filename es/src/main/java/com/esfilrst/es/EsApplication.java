package com.esfilrst.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class EsApplication {

    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(EsApplication.class, args);
    }

}
