package com.tangshengbo.design.observer;

import org.apache.commons.lang.StringUtils;

/**
 * Created by tangshengbo on 2017/2/19.
 */
public class ObserverTest {

    public static void main(String[] args) {
       /* // 建立WeatherData对象
        WeatherSubject subject = new WeatherSubject();

        // 建立目前状况布告板
        Observer observer = new CurrentDisplayObserver(subject);
        subject.setWeather(new Weather(1.2f,13.2f,23.1f));
        subject.setWeather(new Weather(1.2f,13.2f,23.1f));
        subject.setWeather(new Weather(1.2f,13.2f,23.1f));*/

        Observer observer = new NBAObserver();
        System.out.println("正在看NBA..............................");
        Subject subject = new BossSubject();
        subject.attach(observer);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("老板来了............................");
        subject.notifyALl();
        int n = StringUtils.countMatches("1111222211", "1");
        System.out.println(n);

    }
}
