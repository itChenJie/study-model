package com.validation框架;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;

/**
 * @Author ChenWenJie
 * @Classname ValidationTest
 * Describe:
 * @Date 2020/4/14 17:06
 */
public class ValidationTest {
    //验证器对象
    private Validator validator;
    //待验证对象
    private UserInfo userInfo;

    private Set<ConstraintViolation<UserInfo>> validate;

    private Set<ConstraintViolation<UserInfoService>> otherSet;
    @Before
    public void  init(){
        validator = Validation.buildDefaultValidatorFactory()
                .getValidator();

        userInfo = new UserInfo();
        //userInfo.setUserId("123");
        userInfo.setUserName("陈杰");
        userInfo.setPassWord("123456");
        userInfo.setEmail("16901457@qq.com");
        userInfo.setAge(18);
        userInfo.setPhone("15832156549");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2012,10,20);
        userInfo.setBirthday(calendar.getTime());
        userInfo.setInfos(new ArrayList<UserInfo>(){{add(new UserInfo());}});
    }

    @After
    public void print(){
        validate.forEach(item ->{
            System.out.println(item.getMessage());
        });
    }

    @Test
    public void nullValidator(){
        validate = validator.validate(userInfo);
    }


    @Test
    public void groupsValidator(){
        validate = validator.validate(userInfo, UserInfo.RegisterGroup.class);
    }

    @Test
    public void groupSequence(){
        validate = validator.validate(userInfo, UserInfo.Group.class);
    }

    @Test
    public void paramValidator() throws NoSuchMethodException {
        ExecutableValidator executableValidator = validator.forExecutables();
        //待验证对象
        UserInfoService infoService = new UserInfoService();
        Method method = infoService.getClass().getMethod("setUserInfo", UserInfo.class);
        //方法输入参数
        Object[] objects = new Object[]{new UserInfo()};
        //对方法的输入参数进行验证
        otherSet = executableValidator.validateParameters(
                infoService,
                method,
                objects);
    }
    @Test
    public void resultValidator() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ExecutableValidator executableValidator = validator.forExecutables();
        //待验证对象
        UserInfoService infoService = new UserInfoService();
        Method method = infoService.getClass().getMethod("getUserInfo");
        //调用方法返回值
        Object returnValue = method.invoke(infoService);
        //校验方法返回值是否符合约束
        otherSet = executableValidator.validateReturnValue(
                infoService,
                method,
                returnValue
        );
    }

    @Test
    public void constructorValidation() throws NoSuchMethodException {
        ExecutableValidator executableValidator = validator.forExecutables();
        //获取构造方法
        Constructor<UserInfoService> constructor = UserInfoService.class.getConstructor(UserInfo.class);
        Object[] objects = new Object[]{new UserInfo()};
        //校验构造函数的输入参数是否符号约束
        otherSet=executableValidator.validateConstructorParameters(constructor,objects);
    }

    @Test
    public void phoneValidator(){
        validate = validator.validate(userInfo);
    }
}
