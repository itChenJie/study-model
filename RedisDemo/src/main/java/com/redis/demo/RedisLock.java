package com.redis.demo;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/1/26 下午4:43
 **/
@Slf4j
@Component
public class RedisLock {
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void stockLock(){
        RLock lock = redissonClient.getLock("stockLock");
        try{
            /**
             * 获取锁
             */
            if (lock.tryLock(10, TimeUnit.SECONDS)) {
                /**
                 * 查询库存数
                 */
                Integer stock = Integer.valueOf(stringRedisTemplate.opsForValue().get("stockCount"));
                /**
                 * 扣减库存
                 */
                if (stock > 0) {
                    stock = stock - 1;
                    stringRedisTemplate.opsForValue().set("stockCount", stock.toString());
                    log.info("库存扣减成功，剩余库存数量：{}", stock);
                }
            }else {
                log.info("未获取到锁！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
