package com.tangshengbo.tutorial.http;

import jodd.io.NetUtil;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Tang on 2017/7/13.
 */
public class HttpClient {

    public static void main(String[] args) {
//        jdkURL();
//        apacheHttp();
        asyncRequest();
        encodeURL();
    }

    private static void encodeURL() {
        try {
            String url = "http://www.xxx.xxx.com/xxx?name=" + URLEncoder.encode("谢宇", "GBK") + "&otherName=" + URLEncoder.encode("谢宇", "GBK");
            System.out.println(url);
            System.out.println(URLDecoder.decode(url, "GBK"));
            System.out.println(NetUtil.downloadString("http://localhost:8080/finance/fy-check/loan"));
        } catch (IOException e) {
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
    }

    private static void jdkURL() {
        try {
            String[] urls = {"http://localhost:8080/finance/LL-check/recharge",
                    "http://localhost:8080/finance/fy-check/loan"};
            for (String url : urls) {
                URL httpUrl = new URL(url);
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(httpUrl.openStream()));
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
    }

    /**
     * 发送 get请求
     */
    public static void apacheHttp() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            // 创建httpget.
            HttpGet httpget = new HttpGet("http://localhost:8083/producer/currentDateTime?token=10");
            System.out.println("executing request " + httpget.getURI());
            // 执行get请求.
            CloseableHttpResponse response = httpClient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                System.out.println("--------------------------------------");
                // 打印响应状态
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    // 打印响应内容长度
                    System.out.println("Response content length: " + entity.getContentLength());
                    // 打印响应内容
                    System.out.println("Response content: " + EntityUtils.toString(entity));
                }
                System.out.println("------------------------------------");
            } finally {
                response.close();
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void asyncRequest() {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        httpclient.start();

        final CountDownLatch latch = new CountDownLatch(1);
        final HttpGet request = new HttpGet("http://localhost:8080/finance/LL-check/recharge");

        System.out.println(" caller thread id is : " + Thread.currentThread().getId());

        httpclient.execute(request, new FutureCallback<HttpResponse>() {

            public void completed(final HttpResponse response) {
                latch.countDown();
                System.out.println(" callback thread id is : " + Thread.currentThread().getId());
                System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
                try {
                    String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                    System.out.println(" response content is : " + content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public void failed(final Exception ex) {
                latch.countDown();
                System.out.println(request.getRequestLine() + "->" + ex);
                System.out.println(" callback thread id is : " + Thread.currentThread().getId());
            }

            public void cancelled() {
                latch.countDown();
                System.out.println(request.getRequestLine() + " cancelled");
                System.out.println(" callback thread id is : " + Thread.currentThread().getId());
            }

        });
//        latch.countDown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            httpclient.close();
        } catch (IOException ignore) {

        }
        System.out.println("结束OK");
    }
}
