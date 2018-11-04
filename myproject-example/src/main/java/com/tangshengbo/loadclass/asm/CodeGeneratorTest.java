package com.tangshengbo.loadclass.asm;


import org.apache.commons.io.FileUtils;
import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.net.URL;

/**
 * Created by Tangshengbo on 2018/10/23
 */
public class CodeGeneratorTest {

    public static void main(String[] args) throws Exception {
//        operationClass();
//        Account account = new AccountImpl();
//        account.operation();
        createClass();
//        loadClass();
    }

    private static void loadClass() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> aClass = CodeGeneratorTest.class.getClassLoader().loadClass("com.tangshengbo.loadclass.asm.AccountImpl");
        Object instance = aClass.newInstance();
        if (instance instanceof AccountImpl) {
            ((AccountImpl) instance).operation();
        }
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.io.tmpdir"));
        String classPath = System.getProperty("java.class.path");
        String[] strings = classPath.split(";");
        for (String s : strings) {
            System.out.println(s);
        }
    }

    private static void operationClass() {
        try {
            URL classUrl = ClassLoader.getSystemResource("com.tangshengbo.loadclass.asm.AccountImpl".replace('.', '/') + ".class");
            ClassReader cr = new ClassReader(classUrl.openStream());
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            ClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);
            cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
            byte[] data = cw.toByteArray();
            FileUtils.writeByteArrayToFile(FileUtils.toFile(classUrl), data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createClass() {
        try {
            SecureClassGenerator secureClassGenerator = new SecureClassGenerator();
            Account account = secureClassGenerator.generateSecureAccount();
            System.out.println(account);
            account.operation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
