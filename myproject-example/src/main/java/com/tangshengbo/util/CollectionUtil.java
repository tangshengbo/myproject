package com.tangshengbo.util;

import java.util.Collection;

/**
 * Created by TangShengBo on 2017/12/21.
 */
public class CollectionUtil {


    // 判断Collection是否为空
    public static boolean isEmpty(Collection<?> collection) {
        return CollectionUtils.isEmpty(collection);
    }

    // 判断Collection是否非空
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    // 判断是否Map是否为空
    public static boolean isEmpty(Map<?, ?> map){
        return MapUtils.isEmpty(map);
    }

    // 判断Map是否非空
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }
}
