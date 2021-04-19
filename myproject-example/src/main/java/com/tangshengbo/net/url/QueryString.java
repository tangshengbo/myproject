package com.tangshengbo.net.url;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class QueryString {

    private final StringBuilder query = new StringBuilder();

    public QueryString() {
    }

    public void add(String name, String value) {
        query.append('&');
        encode(name, value);
    }

    private void encode(String name, String value) {
        try {
            query.append(URLEncoder.encode(name, "UTF-8"));
            query.append('=');
            query.append(URLEncoder.encode(value, "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("Broken VM does not support UTF-8");
        }
    }

    public String getQuery() {
        return query.substring(1);
    }

    @Override
    public String toString() {
        return getQuery();
    }
}