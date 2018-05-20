package com.tangshengbo.tutorial.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jodd.http.HttpBrowser;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.util.ThreadUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Type;

import static com.alibaba.fastjson.JSON.parseObject;

/**
 * Created by Tangshengbo on 2018/3/30.
 */
public class JoddHttpClient {

    private static final Logger logger = LoggerFactory.getLogger(JoddHttpClient.class);

    @Test
    public void testFileUpload() {
        String url;
        url = "http://localhost:8085/portal/love/upload";
        HttpRequest httpRequest = HttpRequest.post(url).form("file", new File("E:/bpic5942.jpg"));
        HttpResponse httpResponse = httpRequest.send();
        logger.info("{}", httpResponse.body());
    }

    @Test
    public void testRequest() {
        HttpBrowser browser = new HttpBrowser();
        HttpRequest request = HttpRequest.get("www.jd.com");
        HttpResponse response = browser.sendRequest(request);
        logger.info("{}", response.bodyText());
        logger.info("{}", browser.getPage());
    }

    @Test
    public void testVerificationCode() {
        Gson gson = new Gson();
        Runnable run = () -> {
            HttpResponse response = HttpRequest.get("http://localhost:8085/portal/log/id").header("User-Agent", "Jodd HTTP-" + Thread.currentThread().getId()).send();
            logger.info("FastJson:{}", parseObject(response.bodyText(), ResultBean.class).getData());
            ResultBean<Integer> resultBean = gson.fromJson(response.bodyText(), new TypeToken<ResultBean<Long>>() {
            }.getType());
            logger.info("Gson    :{}", resultBean.getData());
        };
        for (int i = 0; i < 5000; i++) {
            new Thread(run, "Thread-" + i).start();
        }
        ThreadUtil.sleep(1000000);
    }

    @Test
    public void testGeneratorId() {
        final Type type = new TypeReference<ResultBean<Long>>(){}.getType();
        for (int i = 0; i < 100; i++) {
            HttpResponse response = HttpRequest.get("http://localhost:8085/portal/log/id").header("User-Agent", "Jodd HTTP-" + Thread.currentThread().getId()).send();
            ResultBean<Long> result =  JSON.parseObject(response.bodyText(), type);
            logger.info("FastJson:{}", result.getData());
        }
    }

    private void charToInt() {
        int x = (int) '9' - '0';
        logger.info("{}", x);
        int i = 39;
        int max = 45;
        int digit;
        int result = 0;
        while (i < max) {
            // Accumulating negatively avoids surprises near MAX_VALUE
            digit = charAt(i++) - '0';

            result *= 10;

            result = result - digit;
        }
        logger.info("{}", -result);
    }

    private char charAt(int idx) {
        String text = " \"code\":200,\"message\":\"SUCCESS\",\"data\":463087";
        return text.charAt(idx);
    }
}
