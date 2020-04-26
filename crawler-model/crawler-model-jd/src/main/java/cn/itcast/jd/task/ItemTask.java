package cn.itcast.jd.task;

import cn.itcast.jd.pojo.Item;
import cn.itcast.jd.service.ItemService;
import cn.itcast.jd.util.HttpUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Author ChenWenJie
 * @Classname ItemTask
 * Describe:
 * @Date 2020/4/19 10:43
 */
@Component
public class ItemTask {
    @Autowired
    private HttpUtils httpUtils;
    @Autowired
    private ItemService itemService;
    public static final ObjectMapper MAPPER = new ObjectMapper();

    @Scheduled(fixedDelay = 1000*100)
    public void itemTask() throws IOException {
        String url ="https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&s=113&click=0&page=";

        for (int i=1;i<10;i=i+2){
            String html=httpUtils.doGetHtml(url+i);
            this.parseHtml(html);
        }
    }

    private void parseHtml(String html) throws IOException {
        Document doc = Jsoup.parse(html);
        Elements spus = doc.select("div#J_goodsList > ul > li");
        for (Element spuEle : spus) {
            //获取spu
            long spuId = Long.parseLong(spuEle.attr("data-spu"));
            //获取商品的sku
            Elements skus = spuEle.select("li.ps-item img");
            for (Element skuEle : skus) {
                //获取商品的sku
                Long skuId = Long.parseLong(skuEle.attr("data-sku"));
                //判断当前商品是否抓取过
                List<Item> itemLis = itemService.findAll(Item.builder()
                        .sku(skuId).build());
                if (itemLis.size()>0){
                    continue;
                }
                Item item = new Item();
                item.setSpu(spuId);
                item.setSku(skuId);
                item.setUrl("https://item.jd.com/"+skuId+".html");
                item.setCreated(new Date());
                item.setUpdated(new Date());
                String innerHtml = httpUtils.doGetHtml(item.getUrl());
                String title = Jsoup.parse(innerHtml).select("div.sku-name").text();
                item.setTitle(title);
                //获取商品价格
                String priceUrl = "https://p.3.cn/prices/mgets?skuIds=J_"+skuId;
                String priceJson = this.httpUtils.doGetHtml(priceUrl);
                //解析json数据获取商品价格
                double price = MAPPER.readTree(priceJson).get(0).get("p").asDouble();
                item.setPrice(price);
                String pic ="https:"+skuEle.attr("data-lazy-img").
                        replace("/n9/","/n1/");
                if (!pic.equals("https:")){
                    String picName = httpUtils.doGetImage(pic);
                    item.setPic(picName);
                }
                itemService.save(item);
            }

        }

    }
}
