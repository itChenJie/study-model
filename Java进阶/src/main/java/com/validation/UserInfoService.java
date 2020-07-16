package main.java.com.validation;

import javax.validation.Valid;

/**
 * @Author ChenWenJie
 * @Classname UserInfoService
 * Describe:
 * @Date 2020/4/18 10:50
 */
public class UserInfoService {

    public UserInfoService(){}

    public UserInfoService(@Valid UserInfo userInfo){}

    public void setUserInfo(@Valid UserInfo userInfo){

    }

    public @Valid
    UserInfo getUserInfo(){
        return new UserInfo();
    }
}
