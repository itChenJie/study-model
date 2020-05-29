package com.springbootdemo.demo.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Annotation 开机启动项
 * @ClassName BootCommandLineRunner
 * @Author ChenWenJie
 * @Data 2020/5/8 5:25 下午
 * @Version 1.0
 **/
@Slf4j
@Component
public class BootCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info(" This is  BootCommandLineRunner .... ");
    }
}
