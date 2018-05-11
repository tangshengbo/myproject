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
public class Forecast {

    private String date;
    private String sunrise;
    private String high;
    private String low;
    private String sunset;
    private int aqi;
    private String fx;
    private String fl;
    private String type;
    private String notice;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getHigh() {
        return high;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getLow() {
        return low;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getSunset() {
        return sunset;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    public int getAqi() {
        return aqi;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getFx() {
        return fx;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getFl() {
        return fl;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getNotice() {
        return notice;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}