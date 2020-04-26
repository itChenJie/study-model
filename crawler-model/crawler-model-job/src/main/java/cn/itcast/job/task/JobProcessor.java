package cn.itcast.job.task;

import cn.itcast.job.pojo.JobInfo;
import cn.itcast.job.service.JobInfoService;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @Author ChenWenJie
 * @Classname JobProcessor
 * Describe:
 * @Date 2020/4/19 22:28
 */
@Component
public class JobProcessor implements PageProcessor {
    @Autowired
    private JobInfoService service;
    @Autowired
    private SpringDataPipeline pipeline;

    private  String url = "https://search.51job.com/list/000000,000000,0000,01%252C32,9,99,java," +
            "2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99" +
            "&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0" +
            "&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
    @Scheduled(initialDelay = 1000, fixedDelay = 1000 * 100)
    public void process() {
        Spider.create(new JobProcessor())
                .addUrl(url)
                .setScheduler(new QueueScheduler()
                .setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)))
                .thread(5)
                .addPipeline(pipeline)//添加输出对象
                .run();

    }
        @Override
    public void process(Page page) {
        //获取页面数据
        List<Selectable> list = page.getHtml().css("div#resultList div.el").nodes();
        if (list.size()==0) {
            //如果为空，表示这是招聘信息详情页保存信息详情
            this.saveJobInfo(page);
        }else {
            //如果有值，表示这是招聘信息列表页
            for (Selectable selectable : list) {
                //获取招聘信息详情页url
                String jobInfoUrl = selectable.links().toString();
                //添加到url任务列表中，等待下载
                page.addTargetRequest(jobInfoUrl);
            }
            //获取下一页的url
            String bkUrl = page.getHtml().css(" div.p_in li.bk").nodes().get(1).links().toString();
            //把url放到任务队列中
            page.addTargetRequest(bkUrl);
        }
    }

    private Site site =  Site.me().setCharset("gbk")
                            .setTimeOut(10*1000)
                            .setRetrySleepTime(3000)//重试时间
                            .setRetryTimes(3);//重试次数

    @Override
    public Site getSite() {
        return site;
    }

    /**
     * 解析页面，获取招聘详情
     * @param page
     */
    private void saveJobInfo(Page page) {
        JobInfo jobInfo = new JobInfo();
        Html html = page.getHtml();
        jobInfo.setCompanyName(html.css("div.cn p.cname a", "text").toString());
        jobInfo.setCompanyAddr(Jsoup.parse(html.css("div.bmsg").nodes().get(1).toString()).text());
        jobInfo.setCompanyInfo(Jsoup.parse(html.css("div.tmsg").toString()).text());
        jobInfo.setJobName(html.css("div.cn h1","text").toString());
        String[] timeArr = html.css("div.cn p.msg","title").toString().split("  |  ");
        jobInfo.setJobAddr(timeArr[0]);
        jobInfo.setJobInfo(Jsoup.parse(html.css("div.job_msg").toString()).text());
        jobInfo.setUrl(page.getUrl().toString());
        Integer[] salary=MathSalary.getSalary(html.css("div.cn strong","text").toString());
        jobInfo.setSalaryMin(salary[0]);
        jobInfo.setSalaryMax(salary[1]);

        if (timeArr!=null&&timeArr.length>0) {
            String time = timeArr[timeArr.length - 1];
            jobInfo.setTime(time.substring(0,time.length()-2));
        }

        //保存数据
        page.putField("jobInfo",jobInfo);

    }

}
