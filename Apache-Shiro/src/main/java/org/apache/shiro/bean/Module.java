package org.apache.shiro.bean;

import lombok.Data;

import java.util.Set;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/1/29 下午4:50
 **/
@Data
public class Module {
    private Integer mid;

    private String mname;

    private Set<Role> roles;
}
