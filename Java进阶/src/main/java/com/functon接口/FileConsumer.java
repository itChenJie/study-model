package com.functon接口;

/**
 * @Author ChenWenJie
 * @Classname FileConsumer
 * Describe:文件处理函数式接口
 * 函数式接口：有且有一个抽象方法的接口，称之为函数式接口
 * 当然接口中可以包含其它的方法(默认，静态，私有)
 *
 * @FunctionalInterface
 *  作用:可以检测接口是否是一个函数式接口
 *      是:编译成功
 *      否:编译失败(接口中没有抽象方法，抽象方法的个数多余1个)
 * @Date 2020/4/10 17:06
 *
 */
@FunctionalInterface
public interface FileConsumer {
    /**
     * 函数式接口抽象
     * @param fileContent 文件内容字符串
     */
    void fileHandler(String fileContent);
}
