package org.apache.shiro.bean;

import lombok.Data;

import java.util.Set;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/1/29 下午4:50
 **/
@Data
public class Role {
    private Integer rid;

    private String rname;

    private Set<User> users;

    private Set<Module> Modules;

}
