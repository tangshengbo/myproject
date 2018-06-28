package com.tangshengbo.tutorial.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tangshengbo.json.Weather;
import com.tangshengbo.util.ZipUtil;
import jodd.util.StringPool;
import jodd.util.ThreadUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.StopWatch;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tangshengbo on 2018/1/19.
 */
public class RestTemplateTest {

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateTest.class);

    private RestTemplate restTemplate;

    private AsyncRestTemplate asyncRestTemplate;

    private String url;

    @Before
    public void init() throws Exception {
        restTemplate = new RestTemplate(createFactory());

        asyncRestTemplate = new AsyncRestTemplate();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
    }

    private ClientHttpRequestFactory createFactory() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);
        return requestFactory;
    }

    @Test
    public void testSyncOrAsync() throws Exception {
        StopWatch watch = new StopWatch();
        watch.start("async");
        url = "http://localhost:8085/portal/account/sync_or_async";
        url = UriComponentsBuilder.fromHttpUrl(url).queryParam("requestType", "异步").build().toUriString();
        ListenableFuture<ResponseEntity<String>> responseEntity = asyncRestTemplate.getForEntity(url, String.class);
        watch.stop();
        logger.info("已经返回:{} s", watch.getTotalTimeSeconds());
        watch.start("future");
        String result = responseEntity.get().getBody();
        watch.stop();
        logger.info("AsyncRestTemplate {}, {} s", result, watch.getTotalTimeSeconds());
        logger.info("{}", watch.prettyPrint());
        //异步调用后的回调函数
//        responseEntity.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
//            //调用失败
//            @Override
//            public void onFailure(Throwable ex) {
//                logger.error("=====rest response faliure======");
//            }
//
//            //调用成功
//            @Override
//            public void onSuccess(ResponseEntity<String> result) {
//                watch.stop();
//                logger.info("--->async rest response success----, result {}, 耗时:{} " + result.getBody(),
//                        watch.getTotalTimeMillis());
//            }
//        });
//        ThreadUtil.sleep(20000);
    }

    @Test
    public void testGet() {
        url = "http://localhost:8085/portal/account/search/{id}";
        logger.info("{}", restTemplate.getForObject(url, String.class, 10));
        Map<String, String> params = new HashMap<>();
        params.put("name", "唐声波");
        params.put("age", "11");
        params.put("id", "11");
        logger.info("{}", restTemplate.getForObject(url, String.class, params));
        url = "http://localhost:8085/portal/account/list";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, "唐声波");
        BufferedWriter fileWriter = null;
        try {
            fileWriter = IOUtils.buffer(new FileWriter("E:/string.txt"));
            fileWriter.write(responseEntity.getBody());
            fileWriter.flush();
        } catch (IOException e) {
            logger.error("{}", ExceptionUtils.getStackTrace(e));
        } finally {
            IOUtils.closeQuietly(fileWriter);
        }
        printLog(responseEntity);

    }

    @Test
    public void testPost() {
        url = "http://localhost/server.html";
        Runnable r = () -> {
            for (int i = 0; i < 10000; i++) {
                ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, "", String.class);
//            logger.info("{}", responseEntity.getStatusCode());
//            logger.info("{}", responseEntity.getHeaders());
                logger.info("{}", responseEntity.getBody());
                ThreadUtil.sleep(100);
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(r).start();
        }
        ThreadUtil.sleep(30000);
    }

    @Test
    public void testExchange() {
        url = "http://localhost:8085/portal/account/list";
        HttpHeaders header = new HttpHeaders();
//        header.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
//        header.setContentType(MediaType);
//        JSONObject params = new JSONObject();
//        params.put("id", "10");
        HttpEntity<String> httpEntity = new HttpEntity<>(header);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        printLog(responseEntity);
    }

    @Test
    public void testDownFile() {
//        url = "http://localhost:8085/portal/account/download-file/{fileName}";
        url = "http://localhost:8085/portal/account/download-file-rest/{fileName}";
        HttpHeaders header = new HttpHeaders();
//        header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        HttpEntity<String> httpEntity = new HttpEntity<>(header);
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, byte[].class, "kk");
        logger.info("{}", responseEntity.getStatusCode());
        logger.info("{}", responseEntity.getHeaders());
        byte[] body = responseEntity.getBody();
        logger.info("{}", body);
//        List<String> stringList = ZipUtil.decompress(new ByteArrayInputStream(body));
//        printLog(stringList);
    }

    @Test
    public void testDecompress() {
        InputStream is = null;
        try {
            is = ZipUtil.decompress(new FileInputStream("E:/writer.zip"));
            List<String> stringList = null;
            if (is != null) {
                stringList = IOUtils.readLines(is, StringPool.UTF_8);
            }
            printLog(stringList);
        } catch (IOException e) {
            logger.error("{}", ExceptionUtils.getStackTrace(e));
        } finally {
            IOUtils.closeQuietly(is);
        }
        ThreadUtil.sleep(100000000);
    }

    @Test
    public void testGetIP() throws Exception {
//        url = "http://ip.chinaz.com/getip.aspx";
//        String result;
//        logger.info("==================Rest===================");
//        result = restTemplate.getForObject(url, String.class);
//        printIP(result);
//        logger.info("==================NetUtil.downloadString===================");
//        result = NetUtil.downloadString(url);
//        printIP(result);
//        logger.info("==================IOUtils.toString===================");
//        result = IOUtils.toString(new URL(url), StringPool.UTF_8);
//        printIP(result);

        logger.info("{}", IpAddressUtil.getLocalIP());

        Runnable r = () -> {
            logger.info("{}", IpAddressUtil.resolveLocalRealIp());
        };

        Runnable r2 = () -> {
            logger.info("{}", IpAddressUtil.resolveLocalIp());
        };
        for (int i = 0; i < 1000; i++) {
            new Thread(r).start();
        }
        for (int i = 0; i < 1000; i++) {
            new Thread(r2).start();
        }
        ThreadUtil.sleep(200000000);
//        logger.info("{}", NetUtil.resolveIpAddress("localhost"));
//        Set<String> strings = IpAddressUtil.resolveLocalIps();
//        strings.stream().forEach(s -> {
//            logger.info("{}", s);
//        });
    }

    @Test
    public void testWeather() {
        url = "https://www.sojson.com/open/api/weather/json.shtml?city=上海";
        Weather weather = restTemplate.getForObject(url, Weather.class);
        logger.info("{}", weather);
    }

    private void printIP(String result) {
        Gson gson = new GsonBuilder().create();
        IpAddress ipAddress = gson.fromJson(result, IpAddress.class);
        logger.info("IP:{} - 地址:{}", ipAddress.getIp(), ipAddress.getAddress());
    }

    private void printLog(ResponseEntity<String> responseEntity) {
        logger.info("{}", responseEntity.getStatusCode());
        logger.info("{}", responseEntity.getHeaders());
        logger.info("{}", responseEntity.getBody());
    }

    private void printLog(List<String> stringList) {
        stringList.forEach(s -> logger.info("{}", s));
    }
}
