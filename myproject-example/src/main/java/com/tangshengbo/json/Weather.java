/**
 * Copyright 2018 bejson.com
 */
package com.tangshengbo.json;

import com.alibaba.fastjson.JSON;

/**
 * Auto-generated: 2018-05-11 12:39:12
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Weather {

    private String date;
    private String message;
    private int status;
    private String city;
    private int count;
    private Data data;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}