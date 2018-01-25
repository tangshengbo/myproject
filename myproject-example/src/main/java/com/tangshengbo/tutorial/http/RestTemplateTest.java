package com.tangshengbo.tutorial.http;

import com.tangshengbo.util.baofoo.util.ZipUtil;
import jodd.util.StringPool;
import jodd.util.ThreadUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
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
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
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
//        url = "https://tgw.baofoo.com/boas/api/fileLoadRequest";
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, "", String.class);
//        logger.info("{}", responseEntity.getStatusCode());
//        logger.info("{}", responseEntity.getHeaders());
//        logger.info("{}", responseEntity.getBody());

        url = "https://vgw.baofoo.com/boas/api/fileLoadNewRequest";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("version", "4.0.0.2");
        params.add("member_id", "100000178");//商户号
        params.add("file_type", "fi");//收款：fi   出款：fo
        params.add("client_ip", "116.247.102.46");//要与服务器IP保持一致
        params.add("settle_date", "2018-01-19");//指定日期的对帐文件（除当天）
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        ResponseEntity<String> responseEntity = null;
        responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        printLog(responseEntity);
    }

    @Test
    public void testExchange() {
        url = "http://localhost:8085/portal/account/search/{id}";
        HttpHeaders header = new HttpHeaders();
//        header.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
//        header.setContentType(MediaType);
//        JSONObject params = new JSONObject();
//        params.put("id", "10");
        HttpEntity<String> httpEntity = new HttpEntity<>(header);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class, 10);
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

    private void printLog(ResponseEntity<String> responseEntity) {
        logger.info("{}", responseEntity.getStatusCode());
        logger.info("{}", responseEntity.getHeaders());
        logger.info("{}", responseEntity.getBody());
    }

    private void printLog(List<String> stringList) {
        stringList.forEach(s -> logger.info("{}", s));
    }
}
