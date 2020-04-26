package com.redis.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author ChenWenJie
 * @Classname RedisUilts
 * Describe:
 * @Date 2019/12/21 14:00
 */
@Component
public class RedisUilts {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setString(String key,String value){
        setString(key, value, Long.valueOf(1*60*60*24));
    }
    /**
     * 为了避嫌数据一直存在内存，所以最好设置一个有效时间
     */
    public void setString(String key,String value,Long timeOut){
        stringRedisTemplate.opsForValue().set(key,value);
        if(timeOut!=null){
            stringRedisTemplate.expire(key,timeOut, TimeUnit.SECONDS);
        }
    }

    public String getString(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }
}
