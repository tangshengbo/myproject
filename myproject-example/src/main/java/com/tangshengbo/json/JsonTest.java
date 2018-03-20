package com.tangshengbo.json;

import com.tangshengbo.thread.User;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;


public class JsonTest {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tang", "唐声波");
        jsonObject.put("sheng", new String[]{"kksk", "kpks"});
        System.out.println(jsonObject.toString());
        HashMap map = new HashMap();
        map.put("a3", "aa");
        map.put("a2", "bb");
        map.put("b1", "cc");
        System.out.println(new JSONObject(map).toString());
        System.out.println(new JSONObject(new User("tangbo")).toString());
        String url = JsonTest.class.getResource("/folder/user.json").getFile();
        System.out.println(url);
        File userJson = new File(url);
        String content = FileUtils.readFileToString(userJson, "UTF-8");
        System.out.println(content);
        System.out.println(new JSONObject(content).get("birthday"));

        System.out.println("=========================================================");
        System.out.println(jsonObject);
        createJson();
    }

    private static void createJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Name", "Apple");
        jsonObject.put("Expiry", "2007/10/11 13:54");
        jsonObject.put("Price", 3.9);
        JSONArray jsonArray = new JSONArray();
        jsonArray.put("Small");
        jsonArray.put("Medium");
        jsonArray.put("Large");
        jsonObject.put("Sizes", jsonArray);
        jsonObject.getJSONArray("Sizes").put("唐波");
        System.out.println(jsonObject);
        System.out.println("=====================================================");
        jsonObject = new JSONObject("{\"Sizes\":[\"Small\",\"Medium\",\"Large\"],\"Price\":3.9,\"Expiry\":\"2007/10/11 13:54\",\"Name\":\"Apple\"}");
        System.out.println(jsonObject);
    }
}
