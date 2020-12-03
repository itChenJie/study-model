package com.spring;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2020/12/3 11:11 上午
 **/
@Slf4j
@Aspect
@Component
public class RabbitMQListenerAspect {

    /**
     * 切点
     */
    @Pointcut("execution(* com.listener.*Listener.*(..))")
    public void basicAck() {
    }

    @Around("basicAck()")
    public void handlerController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("****111****："+proceedingJoinPoint.proceed());
    }
}
