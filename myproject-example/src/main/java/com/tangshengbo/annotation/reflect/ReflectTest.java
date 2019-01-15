package com.tangshengbo.annotation.reflect;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.tangshengbo.json.Account;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


/**
 * Created by Tangshengbo on 2017/10/10.
 */
public class ReflectTest {

    private static final String DB_NAME_KEY = "db_server_name";

    private static final String DB_CONFIG_FILE = "/folder/db.properties";

    public static void main(String[] args) {
        ReflectTest reflectTest = new ReflectTest();
        String s = "oracle";
        DBServer server;
        server = reflectTest.getDbServerByStatic(s);
        System.out.println(server);
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            server = reflectTest.getDbServerByDynamic();
            System.out.println(server);
        }
    }

    private DBServer getDbServerByDynamic() {
        Properties properties = new Properties();
        try {
            InputStream is;
            String path = this.getClass().getResource(DB_CONFIG_FILE).getPath();
//            is = new FileInputStream(path);
//            is = ReflectTest.class.getResourceAsStream("/folder/db.properties");
            is = ClassLoader.getSystemClassLoader().getResourceAsStream("folder/db.properties");
            System.out.println(path);
            System.out.println(is);


            properties.load(is);
//            ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
//            String dbServerName = resourceBundle.getString(DB_NAME_KEY);
//            dbServerName =  properties.getProperty(DB_NAME_KEY);
            return (DBServer) Class.forName(properties.get(DB_NAME_KEY).toString()).newInstance();
        } catch (ReflectiveOperationException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private DBServer getDbServerByStatic(String s) {
        DBServer server = null;
        switch (s) {
            case "oracle":
                server = new OracleServer();
                break;
            case "mysql":
                server = new MySqlServer();
                break;
        }
        return server;
    }

    /**
     * 1.用于获取结果集的映射关系
     */
    private String getResultsStr(Class origin) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("@Results({");
        for (Field field : origin.getDeclaredFields()) {
            String property = field.getName();
            //映射关系：对象属性(驼峰)->数据库字段(下划线)
            String column = new PropertyNamingStrategy.SnakeCaseStrategy().translate(field.getName())
                    .toUpperCase();
            stringBuilder.append(String.format("@Result(property = %s, column = %s)", property, column) + "\n");
        }
        stringBuilder.append("})");
        return stringBuilder.toString();
    }

    @Test
    public void testReflectResult() {
        System.out.println(getResultsStr(Account.class));
    }
}
