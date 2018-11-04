package com.tangshengbo.loadclass.asm;

import org.apache.commons.io.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.net.URL;

/**
 * Created by Tangshengbo on 2018/10/23
 */
public class SecureClassGenerator {

    private static final AccountGeneratorClassLoader classLoader = new AccountGeneratorClassLoader();

    private static Class secureAccountClass;

    public Account generateSecureAccount() throws Exception {
        if (null == secureAccountClass) {
            URL classUrl = ClassLoader.getSystemResource("com.tangshengbo.loadclass.asm.AccountImpl".replace('.', '/') + ".class");
            ClassReader cr = new ClassReader(classUrl.openStream());
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            AddSecurityCheckClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);
            cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
            byte[] data = cw.toByteArray();
            FileUtils.writeByteArrayToFile(new File("D:/tly.class"), data);
            secureAccountClass = classLoader.defineClassFromClassFile("com.tangshengbo.loadclass.asm.AccountImpl$EnhancedByASM", data);
        }
        return (Account) secureAccountClass.newInstance();
    }

    private static class AccountGeneratorClassLoader extends ClassLoader {
        Class defineClassFromClassFile(String className, byte[] classFile) throws ClassFormatError {
            return defineClass(className, classFile, 0, classFile.length);
        }
    }
}
