package com.zerenlian.service;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.zerenlian.dynamicliabilitychain.Handler;
import com.zerenlian.dynamicliabilitychain.HandlerContext;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @Description Excel文件解析器
 * @Author ChenWenJie
 * @Data 2020/12/10 11:10 上午
 **/
@Component
public class AnalysisExcelHandler implements Handler {
    /**
     * 解析文件
     *
     * @param ctx
     * @param object
     */
    @Override
    public void receiveTask(HandlerContext ctx, Object object) {
        ExcelReader excelReader = ExcelUtil.getReader((InputStream) object);
        Map<String, String> headerAlias = excelReader.getHeaderAlias();
        List<List<Object>> read = excelReader.read();
    }

    /**
     * 查询到task之后，进行task过滤的逻辑
     *
     * @param ctx
     * @param object
     */
    @Override
    public void filterTask(HandlerContext ctx, Object object) {
        for (List<Object> objects : (List<List<Object>>) object) {
            for (Object obj : objects) {
                if (obj == null || obj == ""){
                    throw new RuntimeException("参数不能为空");
                }
            }
        }
    }

    /**
     * task过滤完成之后，处理执行task的逻辑
     *
     * @param ctx
     * @param object
     */
    @Override
    public void executeTask(HandlerContext ctx, Object object) {
        System.out.println("数据入库！");
    }

    /**
     * 在整个流程中，保证最后一定会执行的代码，主要是用于一些清理工作
     *
     * @param ctx
     */
    @Override
    public void afterCompletion(HandlerContext ctx) {

    }
}
