package com.tangshengbo.loadclass;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by Tangshengbo on 2019/2/18
 */
public class MyClassLoader extends ClassLoader {

    private String classPath;

    String classname = "com.tangshengbo.loadclass.Person";

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classDate = getDate(name);
        if (classDate == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(classname, classDate, 0, classDate.length);
        }
    }

    private byte[] getDate(String name) {
        String path = classPath + name;
        try {
            InputStream is = new FileInputStream(path);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int num = 0;
            while ((num = is.read(buffer)) != -1) {
                stream.write(buffer, 0, num);
            }
            return stream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            String path = "E:/Users/TangShengBo/intellJidea/myproject/myproject-example/target/classes/com/tangshengbo/loadclass/";
            MyClassLoader load = new MyClassLoader(path);
            Class r = load.findClass("Person.class");
            System.out.println(r.newInstance());
            MyClassLoader load2 = new MyClassLoader(path);
            Class r2 = load2.findClass("Person.class");
            //会报异常：attempted  duplicate class definition for name: "com/tangshengbo/loadclass/Person"，重复加载类
            System.out.println(r2.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}