package com.tangshengbo.json;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tangshengbo.thread.Student;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.util.StopWatch;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GsonTest {

    private static final Logger logger = LoggerFactory.getLogger(GsonTest.class);

    public static void main(String[] args) throws IOException {
        testPerformance();

//
//        Account account = new Account();
//        account.setId(1);
//        account.setMoney(22.32);
//        account.setName("糖糖");
//        account.setHasGrilFriend(false);
//        account.setMajoys(new String[]{"唐阿卡", "卡卡"});
//        account.setBirthday(new Date());
//        GsonBuilder builder = new GsonBuilder();
//        builder.setPrettyPrinting();
//        builder.setFieldNamingStrategy(new FieldNamingStrategy() {
//
//            public String translateName(Field field) {
//
//                return field.getName();
//            }
//        });
//        builder.setDateFormat("yyyy-MM-dd");
//        Gson gson = builder.create();
//        System.out.println(gson.toJson(account));
//        GsonTest gsonTest = new GsonTest();
//        gsonTest.readFileByGson();
    }

    private static void testPerformance() throws IOException {
        //生成较大的json
        List<Student> list = Lists.newLinkedList();
        for (int i = 0; i < 500000000; i++) {
            Student student = new Student();
            student.setAge(i);
            student.setName("唐声波:" + i);
            list.add(student);
        }
        System.out.println("数据成功完毕.................");
        Gson gson = new GsonBuilder().create();
        int number = 20;

        double gsonTime = 0;
        double jackSonTime = 0;
        double fastJsonTime = 0;

        String str = gson.toJson(list);

        for (int i = 0; i < number; i++) {
            System.out.println("-----------------------------------------");

            //fastJson解析
            StopWatch fastJsonWatch = new StopWatch();
            fastJsonWatch.start();
            List l3 = JSON.parseObject(str, new com.alibaba.fastjson.TypeReference<List<Student>>() {
            });
            System.out.println(l3.size());
            fastJsonWatch.stop();
            fastJsonTime += fastJsonWatch.getTotalTimeSeconds();
            System.out.println("fastJson time elapse:" + fastJsonWatch.getTotalTimeSeconds());


            //2,jackson解析
            ObjectMapper mapper = new ObjectMapper();
            StopWatch jackSonWatch = new StopWatch();
            jackSonWatch.start();
            List l2 = mapper.readValue(str, new TypeReference<List<Student>>() {
            });
            System.out.println(l2.size());
            jackSonWatch.stop();
            jackSonTime += jackSonWatch.getTotalTimeSeconds();
            System.out.println("jackson time elapse:" + jackSonWatch.getTotalTimeSeconds());


            //1,gson解析
            StopWatch gsonWatch = new StopWatch();
            gsonWatch.start();
            List l = gson.fromJson(str, new TypeToken<List<Student>>() {
            }.getType());
            System.out.println(l.size());
            gsonWatch.stop();
            gsonTime += gsonWatch.getTotalTimeSeconds();
            System.out.println("gson time elapse:" +  gsonWatch.getTotalTimeSeconds());




        }

        System.out.println("========================================================");
        System.out.println("gson             jackSon              fastJson");
        System.out.println((gsonTime) + "            " + (jackSonTime) + "            " + (fastJsonTime));
        System.out.println((BigDecimal.valueOf(gsonTime).divide(BigDecimal.valueOf(number), 3,  BigDecimal.ROUND_DOWN))
                + "            " + (BigDecimal.valueOf(jackSonTime).divide(BigDecimal.valueOf(number), 3,  BigDecimal.ROUND_DOWN))
                + "            " + (BigDecimal.valueOf(fastJsonTime).divide(BigDecimal.valueOf(number), 3,  BigDecimal.ROUND_DOWN)));

    }

    @Test
    public void readFileByGson() throws IOException {
        String url = JsonTest.class.getResource("/folder/user.json").getFile();
        System.out.println(url);
        File userJson = new File(url);
        String content = FileUtils.readFileToString(userJson, "UTF-8");
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Account account = gson.fromJson(content, Account.class);
        System.out.println(account.toString());
    }

    @Test
    public void toJsonStr() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "tang");
        map.put("age", 18);
        map.put("sex", null);
        String json = new Gson().toJson(map);
        Gson gson = GsonBuilderUtils.gsonBuilderWithBase64EncodedByteArrays()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).serializeNulls().create();
        json = gson.toJson(map);
        logger.info("{}", json);

        MDC.put("first", "Dorothy");

        Logger logger = LoggerFactory.getLogger(GsonTest.class);
        // We now put the last name
        MDC.put("last", "Parker");

        // The most beautiful two words in the English language according
        // to Dorothy Parker:
        logger.info("Check enclosed.");
        logger.debug("The most beautiful two words in English.");

        MDC.put("first", "Richard");
        MDC.put("last", "Nixon");
        logger.info("I am not a crook.");
        logger.info("Attributed to the former US president. 17 Nov 1973.");
    }
}
