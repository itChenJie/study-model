package com.redis.demo;

import com.alibaba.fastjson.JSONObject;
import com.redis.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ChenWenJie
 * @Classname UserController
 * Describe:
 * @Date 2019/12/21 14:24
 */
@RestController
public class UserController {
    @Autowired
    private RedisUilts redisUilts;

    @GetMapping("/addUser")
    public String addUser(User user){
        redisUilts.setString("user", JSONObject.toJSONString(user));
        return "存储成功!";
    }

    @RequestMapping("/getUser")
    public User getUser(String key){
        return JSONObject.parseObject(redisUilts.getString(key),User.class);
    }
}
