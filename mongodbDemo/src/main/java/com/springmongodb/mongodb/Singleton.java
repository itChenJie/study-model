package com.springmongodb.mongodb;

/**
 * @Author ChenWenJie
 * @Classname Singleton
 * Describe:
 * @Date 2020/3/18 22:10
 */
//懒汉式只创建一次
public class Singleton {
    private Singleton(){}
    private String url;

    private String name;

    private static Singleton singleton ;

    static {
        singleton = new Singleton();
        singleton.name = "陈杰";
        singleton.url="127.0.0.1";
    }

    public static Singleton getInstance(){
        return singleton;
    }

}

//饿汉式
class Singleton2{
    private Singleton2(){}
    private String url;

    private String name;

    private static Singleton2 singleton ;

    public static Singleton2 getInstance(){
        if(singleton ==null){
            synchronized(Singleton2.class){
                if(singleton ==null) {
                    singleton = new Singleton2();
                    singleton.name="陈杰";
                    singleton.url="陈杰";
                }
            }
        }
        return singleton;
    }
}

