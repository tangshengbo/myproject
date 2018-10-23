package com.tangshengbo.loadclass.asm;


import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by Tangshengbo on 2018/10/23
 */
public class AddSecurityCheckMethodAdapter extends MethodAdapter {

    public AddSecurityCheckMethodAdapter(MethodVisitor methodVisitor) {
        super(methodVisitor);
    }

    @Override
    public void visitCode() {
        visitMethodInsn(Opcodes.INVOKESTATIC, "com/tangshengbo/loadclass/asm/SecurityChecker",
                "checkSecurity", "()V");
    }
}
