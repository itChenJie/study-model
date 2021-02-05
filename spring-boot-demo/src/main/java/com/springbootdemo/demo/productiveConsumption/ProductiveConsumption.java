package com.springbootdemo.demo.productiveConsumption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/2/3 下午4:09
 **/
@Service
public class ProductiveConsumption {
    @Autowired
    private ProductiveConsumptionThread productiveConsumptionThread;

    public void execute(){
       new Thread(()->{
           for (int i = 0; i < 10; i++) {
               productiveConsumptionThread.produce();
           }
       },"Productive_").start();

       new Thread(() ->{
           for (int i = 0; i < 10; i++) {
               productiveConsumptionThread.consumption();
           }
       },"Consumption_").start();
    }
}
