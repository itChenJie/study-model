package org.apache.shiro.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/1/29 下午3:50
 **/
@Data
public class User implements Serializable {
    private Integer uid;

    private String username;

    private String password;
    private Set<Role> roles;
}
