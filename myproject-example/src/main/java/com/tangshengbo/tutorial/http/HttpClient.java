package com.tangshengbo.tutorial.http;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Tang on 2017/7/13.
 */
public class HttpClient {

    public static void main(String[] args) {
        jdkURL();
//        apacheHttp();
    }

    private static void jdkURL() {
        try {
            String[] urls = {"http://localhost:8080/finance/LL-check/recharge",
                    "http://goldman.houbank.com/finance/xmcg-check/all-balance",
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
}
