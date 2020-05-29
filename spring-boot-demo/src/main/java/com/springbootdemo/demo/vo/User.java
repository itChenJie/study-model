package com.springbootdemo.demo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Annotation
 * @ClassName User
 * @Author ChenWenJie
 * @Data 2020/5/8 9:15 下午
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * 配置不被系列化的属性
 */
//@JsonIgnoreProperties({"address"})
public class User {
    private String name;

    private int age;

    private String address;
    /**
     * @JsonProperty 此注解用于属性上，作用是把该属性的名称序列化为另外一个名称，如把createdDate属性序列化为rt，
     */
    @JsonProperty("rt")
    /**
     * sonFormat是一个时间格式化
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createdDate;
}
