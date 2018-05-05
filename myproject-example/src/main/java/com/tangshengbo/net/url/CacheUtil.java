package com.tangshengbo.net.url;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Tangshengbo on 2018/5/5
 */
public class CacheUtil {

    /**
     * 缓存容器
     */
    private static Map<String, Object> cacheMap = new ConcurrentHashMap<>();

    /**
     * 添加缓存
     * @param key
     * @param value
     * @param second 缓存有效时间 单位(秒)
     * @return
     */
    public static boolean addCache(String key, Object value, int second) {
        System.out.println("添加缓存：key " + key + "\t value " + value);
        cacheMap.put(key, new CacheModel(value, second));
        return true;
    }

    public static Object getValue(String key) {
        System.out.println("获取缓存：key " + key);
        if (cacheMap.containsKey(key)) {
            CacheModel cacheModel = (CacheModel) cacheMap.get(key);
            if (cacheModel.activeTime < System.currentTimeMillis()) {
                System.out.println("缓存已经失效!");
                removeCache(key);
                return null;
            }
            return cacheModel.value;
        }
        return null;
    }

    public static boolean removeCache(String key) {
        System.out.println("移除缓存：key " + key);
        if (cacheMap.containsKey(key)) {
            cacheMap.remove(key);
            return true;
        }
        return false;
    }

    private static class CacheModel {
        long activeTime;
        Object value;

        CacheModel(Object value, int second) {
            this.value = value;
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, second);
            activeTime = calendar.getTimeInMillis();
        }
    }
}
