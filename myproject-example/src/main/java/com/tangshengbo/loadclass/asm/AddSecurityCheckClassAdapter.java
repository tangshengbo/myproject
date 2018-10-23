package com.tangshengbo.loadclass.asm;


import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * Created by Tangshengbo on 2018/10/23
 */
public class AddSecurityCheckClassAdapter extends ClassAdapter {

    public String enhancedSuperName;

    public AddSecurityCheckClassAdapter(ClassVisitor classVisitor) {
        super(classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        MethodVisitor wrappedMv = mv;
        if (mv != null) {
            // 对于 "operation" 方法
            if (name.equals("operation")) {
                // 使用自定义 MethodVisitor，实际改写方法内容
                wrappedMv = new AddSecurityCheckMethodAdapter(mv);
            } else if (name.equals("<init>")) {
                wrappedMv = new ChangeToChildConstructorMethodAdapter(mv, enhancedSuperName);
            }
        }
        return wrappedMv;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        // 改变类命名
        String enhancedName = name + "$EnhancedByASM";
        // 改变父类，这里是”Account”
        enhancedSuperName = name;
        super.visit(version, access, enhancedName, signature,
                enhancedSuperName, interfaces);
    }
}
