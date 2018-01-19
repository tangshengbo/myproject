package com.tangshengbo.tutorial.http;

import org.apache.commons.lang.time.FastDateFormat;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

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
        logger.info("{}", responseEntity.getStatusCode());
        logger.info("{}", FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(responseEntity.getHeaders().getDate()));
        logger.info("{}", responseEntity.getBody());
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


    }
}
