package com.functon接口;

import java.io.IOException;

/**
 * @Author ChenWenJie
 * @Classname FileServiceTest
 * Describe:
 * @Date 2020/4/10 17:18
 */
public class FileServiceTest {
    public static void main(String[] args) throws IOException {
        //通过lambda表达式调用接口
        new FileService()
                .fileHandler("I:\\IDEA\\practice\\src\\com\\util\\GuavaUtil.java",
                        fileContent -> System.out.println(fileContent));
    }

}
