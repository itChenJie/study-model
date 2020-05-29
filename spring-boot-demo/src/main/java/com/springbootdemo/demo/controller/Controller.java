package com.springbootdemo.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootdemo.demo.config.JacksonConfig;
import com.springbootdemo.demo.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Annotation
 * @ClassName Controller
 * @Author ChenWenJie
 * @Data 2020/5/8 9:36 下午
 * @Version 1.0
 **/
@Slf4j
@RestController()
@RequestMapping("/test")
public class Controller {
    @Autowired
    private ObjectMapper mapper;

    @GetMapping("/user")
    public User getUser() throws JsonProcessingException {
        User user =  User.builder()
                .name("测试")
                .address("ces")
                .age(10)
                .createdDate(new Date()).build();
        //系列化
        String jsonString = mapper.writeValueAsString(user);
        log.info("  Controller - jacksonUser:{}",jsonString);
        //反系列化
        return mapper.readValue(jsonString,User.class);
    }

}
