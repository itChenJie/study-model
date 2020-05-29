package com.springbootdemo.demo.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Annotation 开机启动项
 * ApplicationRunner 的默认优先级高于 CommandLineRunner
 * 可以使用@Order(1)来改变优先级
 *
 * 二者的功能和官方文档一模一样，都是在Spring容器初始化完毕之后执行起run方法
 * 不同点在于，前者的run方法参数是String...args，直接传入字符串
 * 后者的参数是ApplicationArguments，对参数进行了封装
 *
 * @ClassName BootApplicationRunner
 * @Author ChenWenJie
 * @Data 2020/5/8 5:22 下午
 * @Version 1.0
 **/

@Slf4j
@Component
public class BootApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("This is BootApplicationRunner....");
    }
}
