package com.tangshengbo.set;

import com.google.common.collect.Lists;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class MapTest {
    public static void main(String[] args) throws UnsupportedEncodingException {

        MapTest mapTest = new MapTest();
        //mapTest.calculate(10);
        //mapTest.testCopyArray();
        mapTest.hashMap();
        // mapTest.linkedMap();
        //mapTest.treeMap();
        //mapTest.testRedis();
        //mapTest.testRedisPool();
        // mapTest.testRedisPools();
        /*byte[] b = new byte[0x16];
        System.out.println(b);
		String temp = new String(b);
		System.out.println(temp);
		System.out.println(Integer.SIZE);
		
		System.out.println(111%4);
		*/


    }

    public void hashMap() {
        HashMap map = new HashMap();

        map.put("a3", "aa");
        map.put("a2", "bb");
        map.put("b1", "cc");
        map.get("");
        map.remove("");

        Iterator it = map.values().iterator();
        while (it.hasNext()) {
            map.remove(it.next());
            System.out.println("没有报错");

        }

       /* Set keys = map.keySet();
        Iterator it = keys.iterator();
        while (it.hasNext()) {
            map.remove(it.next());
            System.out.println("没有报错");
        }*/

    }

    public void linkedMap() {
        Map map = new HashMap();
        map.put("a3", "aa");
        map.put("a2", "bb");
        map.put("b1", "cc");
        Iterator it = map.values().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public void treeMap() {
        Map map = new TreeMap(new Comparator<Object>() {
            public int compare(Object o1, Object o2) {
                return o2.toString().compareTo(o1.toString());
            }
        });
        map.put("a3", "aa");
        map.put("a2", "bb");
        map.put("b1", "cc");
        Iterator it = map.values().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }


    }

    private void testCopyArray() {
        int[] srcArray = new int[100];
        int length = srcArray.length;
        for (int i = 0; i < length; i++) {
            srcArray[i] = i;
        }
        int[] destArray = new int[100];
        System.arraycopy(srcArray, 0, destArray, 0, 100);
        length = destArray.length;
        for (int j = 0; j < length; j++) {

            System.out.print(destArray[j] + " ");
        }

    }

    private void calculate(int number) {

        int calcResult = number << 2;

        String str = "" + 'a';

        System.out.println(calcResult);

        boolean isDone = false;
        int value = 0;
       /* if(isDone){
            value = 1;
        }else{
            value = 10;
        }*/
        value = isDone ? 0 : 10;

        System.out.println(value);
        int max = 100;
        StringBuffer sb = new StringBuffer(0);
        for (int i = 0; i < max + 10000; i++) {
            sb.append("" + i);
        }
        System.out.println(sb.toString());
        List charsets = Lists.newArrayList();
        if (max == 100) {
            String charset = "UTF-8";
            charsets.add(charset);
        }


    }


}
