package com.springbootdemo.demo.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Annotation 自定义异步线程池的配置类
 * @ClassName AsyncPoolConfig
 * @Author ChenWenJie
 * @Data 2020/5/8 4:09 下午
 * @Version 1.0
 **/
@Slf4j
@Configuration
public class AsyncPoolConfig implements AsyncConfigurer {

    @Bean
    @Override
    public Executor getAsyncExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //核心线程大小
        taskExecutor.setCorePoolSize(7);
        //线程池的最大线程数
        taskExecutor.setMaxPoolSize(20);
        //缓存队列的长度
        taskExecutor.setQueueCapacity(20);
        //线程池维护线程所允许的空闲时间
        taskExecutor.setKeepAliveSeconds(60);
        //线程名的前缀
        taskExecutor.setThreadNamePrefix("Async_");
        //表明等待所有线程执行完才能停止服务 默认是false
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        //等待60秒后强制停止
        taskExecutor.setAwaitTerminationSeconds(60);
        //拒绝策略
        taskExecutor.setRejectedExecutionHandler(
                //对拒绝任务抛弃处理，并且抛出异常
                new ThreadPoolExecutor.AbortPolicy()
        );
        return taskExecutor;
    }

    /**
     * 定义异步任务异常处理类
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler(){
        return new AsyncExceptionHandler();
    }

    class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler{

        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            log.info("AsyncError:{}, Method:{}, Param:{}",
                    throwable.getMessage(),method.getName(), JSON.toJSONString(objects));
            throwable.printStackTrace();
            //TODO 发送邮件或者短信
        }
    }
}
