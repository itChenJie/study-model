package com.springbootdemo.demo.controller;

import com.springbootdemo.demo.mall.service.impl.InitServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/2/3 下午5:41
 **/
@Slf4j
@RestController("")
public class ProductController {
    @Autowired
    private InitServiceImpl initService;

    @GetMapping("/product/robbing")
    public String robbingProduct(){
      log.info("开抢");
      initService.generateMultiThread();
      return "OK";
    }
}
