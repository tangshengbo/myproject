package com.tangshengbo.json;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;

public class GsonTest {
	public static void main(String[] args) throws IOException {
		Account account = new Account();
		account.setId(1);
		account.setMoney(22.32);
		account.setName("糖糖");
		account.setHasGrilFriend(false);
		account.setMajoys(new String[] { "唐阿卡", "卡卡" });
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

	public void readFileByGson() throws IOException {

		String url = JsonTest.class.getResource("/folder/user.json").getFile();
		System.out.println(url);
		File userJson = new File(url);

		String content = FileUtils.readFileToString(userJson, "UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Account account = gson.fromJson(content, Account.class);
		System.out.println(account.getBirthday().toString());
		

	}

}
