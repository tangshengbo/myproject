package com.tangshengbo.json;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;

import com.tangshengbo.test.User;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;


public class JsonTest {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("tang", "唐声波");
		jsonObject.put("sheng", new String[]{"kksk","kpks"});
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
		SecureRandom secureRandom = SecureRandom.getInstance("");
		secureRandom.nextLong();
		
		String content = FileUtils.readFileToString(userJson,"UTF-8");
		System.out.println(content);
		System.out.println(new JSONObject(content).toString(2));
	    
		
		
	}
}
