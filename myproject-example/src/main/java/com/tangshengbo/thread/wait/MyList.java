package com.tangshengbo.thread.wait;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by TangShengBo on 2017-11-07.
 */
public class MyList {

    private static List<String> list = Lists.newArrayList();

    public static void add() {
        list.add("anyString");
    }

    public static int size() {
        return list.size();
    }
}
