package com.tangshengbo.jvm.stack;

/**
 * Created by TangShengBo on 2017-05-16.
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF sof = new JavaVMStackSOF();
        try {
            sof.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + sof.stackLength);
//            throw e;
        }
        System.out.println(Runtime.getRuntime().availableProcessors());



    }
}
