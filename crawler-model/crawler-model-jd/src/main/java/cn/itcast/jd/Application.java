package cn.itcast.jd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author ChenWenJie
 * @Classname Application
 * Describe:
 * @Date 2020/4/19 10:08
 */
@SpringBootApplication
//设置开启定时任务
@EnableScheduling
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
