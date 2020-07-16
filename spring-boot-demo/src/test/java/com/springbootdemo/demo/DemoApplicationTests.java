package com.springbootdemo.demo;

import com.springbootdemo.demo.async.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
class DemoApplicationTests {
    @Autowired
    private AsyncService asyncService;
    @Test
    void contextLoads() throws InterruptedException, ExecutionException {
            asyncService.asyncPrint();
            log.info("****main****");
            Future<Integer> future = asyncService.asyncPrintHasResult();
            System.out.println(future.get());
        }


}
