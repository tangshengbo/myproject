package com.tangshengbo.tutorial.http;

import com.alibaba.dubbo.common.utils.NetUtils;
import com.tangshengbo.json.Account;
import jodd.io.NetUtil;
import jodd.util.StringPool;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Tang on 2017/7/13.
 */
public class HttpClient {

    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);

    public static void main(String[] args) {
        System.out.println(NetUtil.resolveIpAddress("www.qq.com"));
        System.out.println(NetUtil.validateHostIp("180.163.26.00"));
//        httpDownload();
//        apacheHttp();
//        asyncRequest();
//        encodeURL();
    }

    private static void httpDownload() {
        try {
            NetUtil.downloadFile("http://download.12306bypass.com/12306Bypass_1.12.69.zip", new File("E:/12306Bypass_1.12.69.zip"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @Test
    public void jdkURL() {
        try {
            String[] urls = {/*"http://localhost:8080/finance/xmcg-check/recharge",*/
                    /*"http://localhost:8080/finance/xmcg-check/all-balance",*/
//                    "http://localhost:8080/finance/fy-check/loan",
                    /*"http://localhost:8080/finance/bf-check/receipt"*/
                    "http://ip.chinaz.com/getip.aspx"};
            for (String url : urls) {
                URL httpUrl = new URL(url);
                URLConnection connection = httpUrl.openConnection();
                String contentType = connection.getContentType();
                Map<String, List<String>> headerMap = connection.getHeaderFields();
                String body = IOUtils.toString(connection.getInputStream(), StringPool.UTF_8);
                logger.info("contentType:{}", contentType);
                logger.info("headerMap:{}", headerMap);
                logger.info("body:{}", body);
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

    @Test
    public void sendPostRequest() {
        String url = "http://localhost:8085/portal/account/list?name=唐声波&age=11";
        String USER_AGENT = "Mozilla/5.0";

//        List<NameValuePair> urlParameters = new ArrayList<>();
//        urlParameters.add(new BasicNameValuePair("name", "唐声波"));
//        urlParameters.add(new BasicNameValuePair("age", "11"));

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(url);
            post.setHeader("User-Agent", USER_AGENT);
//            post.setEntity(new UrlEncodedFormEntity(urlParameters, StringPool.UTF_8));

            CloseableHttpResponse response = httpClient.execute(post);
            logger.info("Response Code : {}", response.getStatusLine().getStatusCode());
            Header[] headers = response.getAllHeaders();
            for (Header header : headers) {
                logger.info("Key:{} Value:{}", header.getName(), header.getValue());
            }
            logger.info("------------------------------------------------------------------");
            logger.info("{}", EntityUtils.toString(response.getEntity()));
//            BufferedReader buffer = IOUtils.buffer(new InputStreamReader(response.getEntity().getContent()));
//            String line;
//            while (true) {
//                line = buffer.readLine();
//                if (Objects.isNull(line)) {
//                    break;
//                }
//                logger.info("返回:{}", line);
//            }
        } catch (IOException e) {
            logger.error("sendPostRequest 异常:{} ", e);
        }
    }

    @Test
    public void restTemplate() {
        //请求地址
        String url = "http://localhost:8085/portal/account/search/{id}";
        RestTemplate restTemplate = new RestTemplate();
        try {
//            logger.info("{}", restTemplate.optionsForAllow(new URI(url)));
            ResponseEntity<Account> responseEntity =  restTemplate.postForEntity(url, "", Account.class, 19);
            logger.info("{}", responseEntity.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> params = new HashMap<>();
        params.put("id", "10");
        logger.info("{}", restTemplate.getForObject(url, Account.class, params));
    }

    @Test
    public void testGetIp() throws Exception {
        logger.info("getLocalHost:{}", NetUtils.getIpByHost("goldman.houbank.com"));
        logger.info("getIpByHost:{}", NetUtils.getLocalHost());
        logger.info("getLocalAddress:{}", NetUtils.getLocalAddress());
        logger.info("resolveIpAddress:{}", NetUtil.resolveIpAddress("goldman.houbank.com"));

        InetAddress netAddress = InetAddress.getLocalHost();
        logger.info("{}", netAddress.getHostAddress());

    }

}
