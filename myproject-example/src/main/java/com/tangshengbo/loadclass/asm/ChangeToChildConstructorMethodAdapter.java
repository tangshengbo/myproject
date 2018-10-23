package com.tangshengbo.loadclass.asm;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by Tangshengbo on 2018/10/23
 */
public class ChangeToChildConstructorMethodAdapter extends MethodAdapter {

    private String superClassName;

    public ChangeToChildConstructorMethodAdapter(MethodVisitor mv, String superClassName) {
        super(mv);
        this.superClassName = superClassName;
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc) {
        // 调用父类的构造函数时
        if (opcode == Opcodes.INVOKESPECIAL && name.equals("<init>")) {
            owner = superClassName;
        }
        // 改写父类为 superClassName
        super.visitMethodInsn(opcode, owner, name, desc);
    }
}