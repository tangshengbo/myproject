package com.tangshengbo.sort;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by tangshengbo on 2017/3/23.
 */
public class MyComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }

    public static void main(String[] args) {
        String str1 = "a";
        String str2 = "b";
        MyComparator comparator = new MyComparator();
        Comparator comparatorTool = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };
        TreeMap<String, String> treeMap = new TreeMap(comparator);
        treeMap.put(str2, str2);
        treeMap.put(str1, str1);
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }

        System.out.println(Integer.parseInt("13abf", 16));
    }
}
