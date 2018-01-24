package com.tangshengbo.tutorial.http;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tangshengbo.util.baofoo.util.ZipUtil.decompress;

/**
 * Created by Tangshengbo on 2018/1/19.
 */
public class RestTemplateTest {

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateTest.class);

    private RestTemplate restTemplate;

    private String url;

    @Before
    public void init() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testGet() {
        url = "http://localhost:8085/portal/account/search/{id}";
        logger.info("{}", restTemplate.getForObject(url, String.class, 10));
        url = "http://localhost:8085/portal/account/list";
        Map<String, String> params = new HashMap<>();
        params.put("name", "唐声波");
        params.put("age", "11");
        logger.info("{}", restTemplate.getForObject(url, String.class, params));
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, "唐声波", 11);
        printLog(responseEntity);
    }

    @Test
    public void testPost() {
//        url = "https://tgw.baofoo.com/boas/api/fileLoadRequest";
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, "", String.class);
//        logger.info("{}", responseEntity.getStatusCode());
//        logger.info("{}", responseEntity.getHeaders());
//        logger.info("{}", responseEntity.getBody());
        url = "http://localhost:8085/portal/account/list";
        ResponseEntity<String> responseEntity = null;
        responseEntity = restTemplate.postForEntity(url, "", String.class);
        printLog(responseEntity);
    }

    @Test
    public void testExchange() {
        url = "http://localhost:8085/portal/account/search/{id}";
        HttpHeaders header = new HttpHeaders();
//        header.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        header.setContentType(MediaType.APPLICATION_JSON);
//        JSONObject params = new JSONObject();
//        params.put("id", "10");
        HttpEntity<String> httpEntity = new HttpEntity<>(header);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class, 10);
        printLog(responseEntity);
    }

    @Test
    public void testDownFile() {
        url = "http://localhost:8085/portal/account/download-file/{fileName}";
//        url = "http://localhost:8085/portal/account/download-file-rest/{fileName}";
        HttpHeaders header = new HttpHeaders();
//        header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        HttpEntity<String> httpEntity = new HttpEntity<>(header);
        ResponseEntity<byte[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, byte[].class, "kk");
        logger.info("{}", responseEntity.getStatusCode());
        logger.info("{}", responseEntity.getHeaders());
        byte[] body = responseEntity.getBody();
        logger.info("{}", body);
        List<String> stringList = null;
        try {
            stringList = IOUtils.readLines(new ByteArrayInputStream(body), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        printLog(stringList);
    }

    @Test
    public void testDecompress() {
        try {
            List<String> stringList = decompress(new FileInputStream("E:/writer.zip"));
            printLog(stringList);
        } catch (FileNotFoundException e) {
            logger.error("{}", ExceptionUtils.getStackTrace(e));
        }
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
