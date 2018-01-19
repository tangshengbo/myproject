package com.tangshengbo.util.baofoo.http;

import jodd.util.StringPool;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Map;

public class HttpSendModel {

    private String url;
    private String charset;
    private HttpMethod method;
    private Map<String, String> params;

    public HttpSendModel(String url, HttpMethod method, Map<String, String> params, String charset) {
        this.url = url;
        this.method = method;
        this.params = params;
        this.charset = charset;
    }

    public HttpSendModel(String url, HttpMethod method, Map<String, String> params) {
        this.url = url;
        this.method = method;
        this.params = params;
        this.charset = StringPool.UTF_8;
    }

    public String getUrl() {
        return url;
    }

    public String getCharset() {
        return charset;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public Map<String, String> getParams() {
        return params;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
