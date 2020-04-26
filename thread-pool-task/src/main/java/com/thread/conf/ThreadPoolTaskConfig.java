package com.thread.conf;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author ChenWenJie
 * @Classname ThreadPoolTaskConfig
 * Describe:线程池配置
 * @Date 2020/4/24 10:08
 */
@Configurable
@EnableAsync
public class ThreadPoolTaskConfig {
    /**
     *   默认情况下，在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，
     *    当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中；
     *  当队列满了，就继续创建线程，当线程数量大于等于maxPoolSize后，开始使用拒绝策略拒绝
     */

    /** 核心线程数（默认线程数） */
    private static final int corePoolSize =2;
    /** 最大线程数 */
    private static final int maxPoolSize=5;
    /** 允许线程空闲时间（单位：默认为秒） */
    private static final int keepAliveTime=10;
    /** 缓冲队列大小 */
    private static final int queueCapacity=10;
    /** 线程池名前缀 */
    private static final String threadNamePrefix = "Async-Service-";

    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        poolTaskExecutor.setCorePoolSize(corePoolSize);
        poolTaskExecutor.setMaxPoolSize(maxPoolSize);
        poolTaskExecutor.setKeepAliveSeconds(keepAliveTime);
        poolTaskExecutor.setQueueCapacity(queueCapacity);
        poolTaskExecutor.setThreadNamePrefix(threadNamePrefix);
        // 线程池对拒绝任务的4中处理策略
        // CallerRunsPolicy：由调用线程（提交任务的线程）处理该任务 (用于被拒绝任务的处理程序，它直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务。)
        //AbortPolicy 抛出java.util.concurrent.RejectedExecutionException异常
        //
        poolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return poolTaskExecutor;

    }
}
