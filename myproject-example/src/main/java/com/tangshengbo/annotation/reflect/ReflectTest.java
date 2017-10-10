package com.tangshengbo.annotation.reflect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Created by Tangshengbo on 2017/10/10.
 */
public class ReflectTest {

    public static void main(String[] args) {
        String s = "oracle";
        DBServer server;
        server = getDbServerByStatic(s);
        System.out.println(server);
        server = getDbServerByDynamic();
        System.out.println(server);
    }

    private static DBServer getDbServerByDynamic() {
        try {
            Properties properties = new Properties();
            String path = ReflectTest.class.getResource("/db.properties").getPath();
            InputStream is = new FileInputStream(path);
            properties.load(is);
            return (DBServer) Class.forName(properties.getProperty("db_server_name")).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static DBServer getDbServerByStatic(String s) {
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
}
