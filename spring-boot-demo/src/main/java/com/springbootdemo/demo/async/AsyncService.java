package com.springbootdemo.demo.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Annotation 异步服务
 * @ClassName AsyncService
 * @Author ChenWenJie
 * @Data 2020/5/8 4:00 下午
 * @Version 1.0
 **/
@Slf4j
@Service
public class AsyncService {

    @Async("getAsyncExecutor")
    public void asyncPrint() throws InterruptedException {
        log.info("async  task thread name ->{}",Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(2);
    }

    @Async("getAsyncExecutor")
    public Future<Integer> asyncPrintHasResult() throws InterruptedException {
        log.info("async asyncPrintHasResult  task thread name ->{}",Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(2);
        return new AsyncResult<>(100);
    }
}
