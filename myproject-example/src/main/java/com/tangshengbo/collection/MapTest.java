package com.tangshengbo.collection;

import com.google.common.collect.Lists;
import com.tangshengbo.thread.Student;
import jodd.util.ThreadUtil;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;


public class MapTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
//        testPutIfAbsent();
        testConvertMap();
//        testLambdaCollect();
//        testDealLoop();
//        System.out.println(ConvertUtils.fieldToColumn("userName"));
    }

    public void hashMap() {
        Map map = new ConcurrentHashMap();
        map.containsValue("");
        map.put("a3", "aa");
        map.put("a2", "bb");
        map.put("b1", "cc");
        map.put("n", "n");


//        Iterator it = map.values().iterator();
//        while (it.hasNext()) {
//            map.remove(it.next());
//            System.out.println("没有报错");
//
//        }

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
        int[] srcArray = new int[1000];
        int length = srcArray.length;
        for (int i = 0; i < length; i++) {
            srcArray[i] = i;
        }
        int[] destArray = new int[1000];
        System.arraycopy(srcArray, 0, destArray, 0, 1000);
        System.out.println(Arrays.toString(destArray));
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
        value = 10;
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

    private static void testPutIfAbsent() {
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("task", "runA");
        concurrentHashMap.putIfAbsent("task", "run");
        for (Map.Entry<String, String> entry : concurrentHashMap.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= "
                    + entry.getValue());
        }
    }

    private static void testConvertMap() {
        List<String> strings = Lists.newArrayList();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        Map<String, String> stringMap = ConvertUtils.toMap(strings, "");
        System.out.println(stringMap.get("4"));
        stringMap.forEach((k, v) -> System.out.println("Key : " + k + " \t Value : " + v));
        System.out.println("==================================================");
        List<Student> students = Lists.newArrayList();
        students.add(new Student(12, "tang"));
        students.add(new Student(13, "sheng"));
        students.add(new Student(14, "bo"));
        try {
            Map<String, Student> studentMap = ConvertUtils.toMapByLambda(students, "name");
            studentMap.forEach((k, v) -> System.out.println("Key : " + k + " \t Value : " + v));
        } catch (Exception e) {
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
    }

    private static void testDealLoop() {
        final HashMap<String, String> map = new HashMap<String, String>(2);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                        }
                    }, "ftf" + i).start();
                }
            }
        }, "ftf");
        t.start();
        ThreadUtil.join(t);
    }

    private static void testLambdaCollect() {
        List<String> strings = Lists.newArrayList();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        Map<String, String> stringMap = strings.stream().collect(Collectors.toMap(Function.identity(), Function.identity()));
        stringMap.forEach((k, v) -> System.out.println("Key : " + k + " \t Value : " + v));
        System.out.println("=================================================================");
        List<Student> students = Lists.newArrayList();
        students.add(new Student(12, "tang"));
        students.add(new Student(13, "sheng"));
        students.add(new Student(14, "bo"));
        Map<String, Student> studentMap;

//        studentMap = students.stream().collect(HashMap<String, Student>::new,
//                (m, c) -> m.put(c.getName(), c),
//                (m, u) -> {
//                });
        studentMap = students.stream().collect(Collectors.toMap(Student::getName, Function.identity()));
        studentMap.forEach((k, v) -> System.out.println("Key : " + k + " \t Value : " + v));
    }
}
