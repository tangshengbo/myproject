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

    public static void main(String[] args) {
//        operationClass();
//        Account account = new AccountImpl();
//        account.operation();
        createClass();
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
