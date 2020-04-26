package cn.itcast.jd.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @Author ChenWenJie
 * @Classname HttpUtils
 * Describe:
 * @Date 2020/4/19 10:11
 */
@Component
public class HttpUtils {

    private PoolingHttpClientConnectionManager me;

    public HttpUtils() {
        this.me = new PoolingHttpClientConnectionManager();
        //设置连接池的最大连接数
        this.me.setMaxTotal(200);
        //设置每个主机的并发数
        this.me.setDefaultMaxPerRoute(20);
    }

    public String doGetHtml(String url){
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(this.me).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:62.0) Gecko/20100101 Firefox/62.0");
        httpGet.setConfig(this.getConfig());
        CloseableHttpResponse httpResponse =null;
        try {
            httpResponse = httpClient.execute(httpGet);
            if(httpResponse.getStatusLine().getStatusCode()==200){
                if (httpResponse.getEntity() != null) {
                    String content = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                    return content;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (httpResponse!=null){
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public String doGetImage(String url){
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(this.me).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(this.getConfig());
        CloseableHttpResponse httpResponse =null;
        try {
            httpResponse = httpClient.execute(httpGet);
            // 解析response下载图片
            if(httpResponse.getStatusLine().getStatusCode()==200){
                // 获取文件类型
                if (httpResponse.getEntity() != null) {
                   String extName = url.substring(url.lastIndexOf("."));
                    // 使用uuid生成图片名
                    String picName = UUID.randomUUID().toString()+extName;
                    // 声明输出的文件
                    OutputStream outputStream = new FileOutputStream(
                            new File("H:/JDImage/"+picName));
                    // 使用响应体输出文件
                    httpResponse.getEntity().writeTo(outputStream);
                    return picName;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (httpResponse!=null){
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }
    private RequestConfig  getConfig(){
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(1000) // 设置创建连接的超时时间
                .setConnectionRequestTimeout(500) // 设置获取连接的超时时间
                .setSocketTimeout(10000)// 设置连接的超时时间
                .build();
        return config;
    }
}
