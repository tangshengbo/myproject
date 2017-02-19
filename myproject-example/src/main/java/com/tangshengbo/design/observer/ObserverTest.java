package com.tangshengbo.design.observer;

/**
 * Created by tangshengbo on 2017/2/19.
 */
public class ObserverTest {

    public static void main(String[] args) {
        // 建立WeatherData对象
        WeatherSubject subject = new WeatherSubject();

        // 建立目前状况布告板
        Observer observer = new CurrentDisplayObserver(subject);
        subject.setWeather(new Weather(1.2f,13.2f,23.1f));
        subject.setWeather(new Weather(1.2f,13.2f,23.1f));
        subject.setWeather(new Weather(1.2f,13.2f,23.1f));

    }
}
