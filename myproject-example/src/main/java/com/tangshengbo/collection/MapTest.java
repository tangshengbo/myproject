package com.tangshengbo.collection;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tangshengbo.json.Account;
import com.tangshengbo.thread.Student;
import jodd.util.ThreadUtil;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.StopWatch;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;


public class MapTest {

    private static final Logger logger = LoggerFactory.getLogger(MapTest.class);

    public static void main(String[] args) throws UnsupportedEncodingException {
//        testPutIfAbsent();
        testConvertMap();
//        testLambdaCollect();
//        testDealLoop();

    }
    @Test
    public void testLinkedMap() {
        final int cacheSize = 10;
        Map<String, String> map = new LinkedHashMap<String, String>((int) Math.ceil(cacheSize / 0.75f) + 1, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > cacheSize;
            }
        };

        for (int i = 0; i < 100; i++) {
            map.put(String.valueOf(i), String.valueOf(i));
        }
        System.out.println(map);
        for (int i = 0; i < 100; i++) {
            map.get(String.valueOf(91));
        }
        System.out.println(map);

        map = new TreeMap<>(map);
        System.out.println(map);
    }

    @Test
    public void beanToMap() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Account account = new Account();
        account.setObject(new Object());
        account.setTitleList(Lists.newArrayList());
        account.setCommentMap(Maps.newHashMap());
        account.setAge(0);
        account.setBirthday(new Date());
        account.setMajoys(new String[]{""});
        account.setHasGrilFriend(false);
        account.setId(0);
        account.setName("");
        account.setMoney(0.0D);
        for (int i = 0; i < 1000; i++) {
            BeanMap beanMap = BeanMap.create(account);
            logger.info("{}", beanMap);
        }
        stopWatch.stop();
        logger.info("耗时：{}s", stopWatch.getTotalTimeSeconds());
    }

    public void hashMap() {
        Map<String, String> map = new ConcurrentHashMap<>();
        map.containsValue("");
        map.put("a3", "aa");
        map.put("a2", "bb");
        map.put("b1", "cc");
        map.put("n", "n");

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
        Map<String, String> map = new LinkedHashMap<>();
        map.put("a3", "aa");
        map.put("a2", "bb");
        map.put("b1", "cc");
        map.forEach((k, v) -> System.out.println(String.format("key %s  value %s", k, v)));
    }

    public void treeMap() {
        Map<String, String> map = new TreeMap<>(String::compareTo);
        map.put("a3", "aa");
        map.put("a2", "bb");
        map.put("b1", "cc");
        map.forEach((k, v) -> System.out.println(String.format("key %s  value %s", k, v)));
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
        strings.add("3");
        strings.add("3");
        strings.add("4");
        Map<String, String> stringMap = ConvertUtils.toMap(strings, "");
        System.out.println(stringMap.get("4"));
        stringMap.forEach((k, v) -> System.out.println("Key : " + k + " \t Value : " + v));
        System.out.println("==================================================");
        int size = 20000000;
        List<Student> students = Lists.newArrayListWithExpectedSize(size);
        for (int i = 0; i < size; i++) {
            students.add(new Student(i, "tang".concat(String.valueOf(i))));
        }
        try {
            long ts = System.currentTimeMillis();
            Map<String, Student> studentMap;
//            studentMap = ConvertUtils.toMapByLambda(students, Student::getName);
            studentMap = ConvertUtils.toMap(students, "name");
            long te = System.currentTimeMillis();
            System.out.println(String.format("+ cost %s ms size %s", (te - ts) / 1000.0, studentMap.size()));
//            studentMap.forEach((k, v) -> System.out.println("Key : " + k + " \t Value : " + v));
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
        students.add(new Student(13, "tang"));
        students.add(new Student(14, "bo"));
        Map<String, Student> studentMap;

//        studentMap = students.stream().collect(HashMap<String, Student>::new,
//                (m, c) -> m.put(c.getName(), c),
//                (m, u) -> {
//                });
        studentMap = students.stream().collect(Collectors.toMap(Student::getName, Function.identity(), (key1, key2) -> key2));
        studentMap.forEach((k, v) -> System.out.println("Key : " + k + " \t Value : " + v));
    }

    @Test
    public void testReflectInvoke() throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(123);
        list.add(456);
        list.add(789);
        // list.add("abc") 如何存进去呢？
        /*
         * 使用反射技术绕过泛型编译期间检查，将不同的数据类型 存储到带有泛型的集合 （泛型的擦除）
         */
        // 获取list字节文字对象
        Class clazz = list.getClass();
        System.out.println(clazz);

        // 存储对象 调用的是集合中的 add()方法 那么通过反射获取add方法
        @SuppressWarnings("unchecked")
        Method addMethod = clazz.getMethod("add", Object.class);
        addMethod.setAccessible(true);
        System.out.println(addMethod);

        //对象有了   方法有了 运行add方法
        addMethod.invoke(list, "abc");
        addMethod.invoke(list, "sss");
        addMethod.invoke(list, "qqq");
        @SuppressWarnings("unchecked")
        Method removeMethod = clazz.getMethod("remove", Object.class);
        removeMethod.setAccessible(true);
        System.out.println(removeMethod);
        removeMethod.invoke(list, "qqq");

        for (Object aList : list) {
            System.out.println(aList);
        }
    }
}
