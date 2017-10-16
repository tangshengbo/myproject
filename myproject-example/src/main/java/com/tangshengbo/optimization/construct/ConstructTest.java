package com.tangshengbo.optimization.construct;

/**
 * Created by Tangshengbo on 2017/10/16.
 */
public class ConstructTest {

    public static void main(String[] args) {
//        Son son = new Son();
//        son.doSomething();

        new Base();
        new Base("");
        new Base(1);
        System.out.println("实例对象的数量：" + Base.getCount());

    }
}

class Father {

    public Father() {
        new Other();
    }
}

class Son extends Father {

    public void doSomething() {
        System.out.println("Hi show me something");
    }
}

class Other {

    public Other() {
        new Son();
    }
}

class Base {

    private static int count = 0;

    {
        count++;
    }

    public Base() {
    }

    public Base(String arg) {
        this();
    }

    public Base(int arg) {

    }

    public static int getCount() {
        return count;
    }
}
