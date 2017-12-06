package com.tangshengbo.design.flyweight;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by Tangshengbo on 2017/10/12.
 */
public class WebSiteFactory {

    private Map<String, WebSite> map = Maps.newHashMap();

    public WebSite getWebSite(String key) {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        WebSite webSite = new ConcreteWebSite(key);
        map.put(key, webSite);
        return webSite;
    }

    public int getWebSiteCount() {
        return map.size();
    }
}
