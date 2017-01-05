package com.tangshengbo.cache;

import com.tangshengbo.model.User;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by tangshengbo on 2017/1/4.
 */
public class MyCache implements Cache {

    private String name;

    private Map<String, User> store = new ConcurrentHashMap<>();

    public MyCache() {

    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        return store;
    }

    @Override
    public ValueWrapper get(Object key) {

        ValueWrapper result = null;
        User thevalue = store.get(key);
        if (thevalue != null) {
            thevalue.setPassword("from mycache:" + name);
            result = new SimpleValueWrapper(thevalue);
        }

        return result;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {

        System.out.println("put" + key);

        User user = (User) value;
        String keyName = (String) key;

        store.put(keyName, user);
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        return null;
    }

    @Override
    public void evict(Object key) {
        System.out.println("e" + key);
        this.store.remove(key);

    }

    @Override
    public void clear() {
        System.out.println("clear");
        this.store.clear();
    }
}
