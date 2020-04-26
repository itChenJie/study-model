package com.functon接口;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author ChenWenJie
 * @Classname FileService
 * Describe:文件服务类
 * @Date 2020/4/10 17:03
 */
public class FileService {

    public void fileHandler(String url,FileConsumer fileConsumer) throws IOException {
        //创建文件读取流
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(url)
                )
        );
        //定义行变量和内容sb
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        //循环读取文件内容
        while ((line=bufferedReader.readLine())!=null){
            stringBuilder.append(line+"\n");
        }
        //调用函数式接口f方法，将文件内容传递给lambda表达式，实现业务逻辑
        fileConsumer.fileHandler(stringBuilder.toString());
    }
}
