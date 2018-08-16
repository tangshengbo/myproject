package com.tangshengbo.json;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GsonTest {

    private static final Logger logger = LoggerFactory.getLogger(GsonTest.class);

    public static void main(String[] args) throws IOException {
        Account account = new Account();
        account.setId(1);
        account.setMoney(22.32);
        account.setName("糖糖");
        account.setHasGrilFriend(false);
        account.setMajoys(new String[]{"唐阿卡", "卡卡"});
        account.setBirthday(new Date());
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.setFieldNamingStrategy(new FieldNamingStrategy() {

            public String translateName(Field field) {

                return field.getName();
            }
        });
        builder.setDateFormat("yyyy-MM-dd");
        Gson gson = builder.create();
        System.out.println(gson.toJson(account));
        GsonTest gsonTest = new GsonTest();
        gsonTest.readFileByGson();
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
    }
}
