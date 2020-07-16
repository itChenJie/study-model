package main.java.com.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;
import javax.validation.groups.Default;
import java.util.Date;
import java.util.List;

/**
 * @Author ChenWenJie
 * @Classname UserInfo
 * Describe:待验证对象实体类
 * @Date 2020/4/14 16:37
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    //登录场景
    public interface LoginGroup{}
    //注册场景
    public interface RegisterGroup{}
    //组排序
    @GroupSequence({
            LoginGroup.class,
            RegisterGroup.class,
            Default.class
    })
    public interface Group{}
    @NotNull(message = "用户id不能为空" , groups = LoginGroup.class)
    private String userId;

    @NotEmpty(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "用户密码不能为空")
    @Length(min = 6,max = 20,message = "密码长度不能小于6位大于20位")
    private String passWord;

    @NotNull(message = "邮箱不能为空", groups = RegisterGroup.class)
    @Email(message = "邮箱必须为有效邮箱")
    private String email;

    @Min(value = 18,message = "年龄不能小于18岁")
    @Max(value = 60,message = "年龄不能大于60岁")
    private Integer age;

    @com.validation框架.Phone(message = "手机号校验错误")
    private String phone;

    @Past(message = "生日不能是一个未来日期")
    private Date birthday;

    @Size(min=1,message = "集合长度不能小于1")
    private List<UserInfo> infos;


}
