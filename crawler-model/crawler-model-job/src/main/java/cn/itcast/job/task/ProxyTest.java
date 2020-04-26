package cn.itcast.job.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

/**
 * @Author ChenWenJie
 * @Classname ProxyTest
 * Describe:
 * @Date 2020/4/20 14:53
 */
@Component
public class ProxyTest implements PageProcessor {
    @Scheduled(fixedDelay = 1000)
    @Override
    public void process(Page page) {
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("101.110.119.70",80)));
        Spider.create(new ProxyTest())
                .addUrl("http://ip.chinaz.com/getip.aspx")
                .setDownloader(httpClientDownloader)
                .run();
    }

    @Override
    public Site getSite() {
        return null;
    }
}
