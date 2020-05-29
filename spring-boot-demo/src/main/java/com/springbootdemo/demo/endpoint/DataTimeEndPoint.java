package com.springbootdemo.demo.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Annotation 自定义事件端点
 * @ClassName DataTimeEndPoint
 * @Author ChenWenJie
 * @Data 2020/5/8 10:44 下午
 * @Version 1.0
 **/
@Endpoint(id = "dataTime")
public class DataTimeEndPoint {
    private String format ="yyyy-MM-dd HH:mm:ss";

    /**
     * 用来显示监控指标
      * @return
     */
    @ReadOperation
    public Map<String,Object> info() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","测试");
        map.put("age","20");
        map.put("dateTime",new SimpleDateFormat(format).format(new Date()));
        return map;
    }

    /**
     * 动态更改监控指标 post方式访问
     * @param format
     */
    @WriteOperation
    public void setFormat(String format){
        this.format = format;
    }

}
