/**
 * @author Administrator
 * @author Administrator
 * @author Administrator
 * @author Administrator
 * @author Administrator
 * @author Administrator
 * @author Administrator
 * @author Administrator
 * @author Administrator
 * @author Administrator
 * @author Administrator
 * @author Administrator
 * @author Administrator
 * @author Administrator
 * @author Administrator
 * @author Administrator
 */
/**
 * @author Administrator
 *
 */
package com.tangshengbo.util.http;

import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 发送Post请求
     * @param url
     * @param params
     * @return
     */
    public static String post(String url, Map<String, String> params) {
        logger.info("发送post请求：地址:{} 参数:{}", url, params);
        HttpSendModel httpSendModel = new HttpSendModel(url, HttpMethod.POST, params);
        SimpleHttpResponse response;
        try {
            response = invoke(httpSendModel);
        } catch (Exception e) {
            logger.error("发送post请求异常:{}", e);
            return e.getMessage();
        }
        if (response.isRequestSuccess()) {
            return response.getEntityString();
        }
        return response.getErrorMessage();
    }

    private static SimpleHttpResponse invoke(HttpSendModel httpSendModel) throws Exception {
        // 创建默认的httpClient客户端端
        SimpleHttpClient simpleHttpclient = new SimpleHttpClient(httpSendModel);
        try {
            return sendRequest(simpleHttpclient);
        } finally {
            simpleHttpclient.getHttpclient().getConnectionManager().shutdown();
        }
    }


    /**
     *  发送请求
     * @param simpleHttpclient
     * @return
     * @throws Exception
     */
    public static SimpleHttpResponse sendRequest(SimpleHttpClient simpleHttpclient) throws Exception {
        HttpSendModel httpSendModel = simpleHttpclient.getHttpSendModel();
        //构建请求对象
        HttpRequestBase httpRequest = buildHttpRequest(httpSendModel);
        //开启SSL
        enableSSL(simpleHttpclient, httpSendModel.getUrl());
        HttpResponse response;
        try {
            //发送请求
            response = simpleHttpclient.getHttpclient().execute(httpRequest);
            return parseResponse(response, httpSendModel.getCharset());
        } catch (Exception e) {
            throw new RuntimeException("http请求异常", e);
        }
    }

    /**
     *  解析http响应
     * @param charset
     * @param response
     * @return
     * @throws IOException
     */
    private static SimpleHttpResponse parseResponse(HttpResponse response, String charset) throws IOException {
        HttpEntity httpEntity = response.getEntity();
        StatusLine statusLine = response.getStatusLine();
        String content = EntityUtils.toString(httpEntity, charset);
        return new SimpleHttpResponse(statusLine.getStatusCode(), content, statusLine.getReasonPhrase());
    }

    /**
     * 开启SSL根据url
     * @param simpleHttpclient
     * @param url
     */
    private static void enableSSL(SimpleHttpClient simpleHttpclient, String url) {
        if (url.startsWith("https://")) {
            simpleHttpclient.enableSSL();
        }
    }

    /**
     * 构建请求参数
     * @param httpSendModel
     * @return
     * @throws Exception
     */
    private static HttpRequestBase buildHttpRequest(
            HttpSendModel httpSendModel) throws Exception {
        if (httpSendModel.getMethod() == HttpMethod.POST) {
            return buildHttpPost(httpSendModel.getUrl(), httpSendModel.getParams(), httpSendModel.getCharset());
        }
        throw new IllegalArgumentException("请求方式不支持：" + httpSendModel.getMethod());
    }

    /**
     *  构建post请求参数
     * @param url
     * @param params
     * @param charset
     * @return
     * @throws UnsupportedEncodingException
     */
    private static HttpPost buildHttpPost(String url, Map<String, String> params, String charset)
            throws UnsupportedEncodingException {
        HttpPost post = new HttpPost(url);
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        params.forEach((k, v) -> nameValuePairs.add(new BasicNameValuePair(k, v)));
        post.setEntity(new UrlEncodedFormEntity(nameValuePairs, charset));
        return post;
    }

    /**
     * 请求是否成功
     *
     * @param statusCode
     * @return
     */
    public static boolean isRequestSuccess(int statusCode) {
        return statusCode == HttpStatus.SC_OK;
    }
}