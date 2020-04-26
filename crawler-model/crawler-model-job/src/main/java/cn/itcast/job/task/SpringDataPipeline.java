package cn.itcast.job.task;

import cn.itcast.job.pojo.JobInfo;
import cn.itcast.job.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @Author ChenWenJie
 * @Classname SpringDataPipeline
 * Describe:
 * @Date 2020/4/19 23:16
 */
@Component
public class SpringDataPipeline implements Pipeline {
    @Autowired
    private JobInfoService service;
    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取封装好的招聘数据
        JobInfo jobInfo =resultItems.get("jobInfo");
        //判断数据是否为空
        if (jobInfo!=null) {
            service.save(jobInfo);
        }
        //如果不为空则保存
    }
}
