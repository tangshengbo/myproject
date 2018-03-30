package com.tangshengbo.tutorial.http;

import jodd.http.HttpBrowser;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

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
}
