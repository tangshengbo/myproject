package com.tangshengbo.innerclass;

/**
 * Created by tangshengbo on 2017/3/14.
 */
public class InnerClassTest {

    public static void main(String[] args) {
        {
            Out.In in = new Out().new In();
            in.print();
        }

        {
            StaticOut.In in = new StaticOut.In();
            in.print();
        }

        {
            PrivateOut out = new PrivateOut();
            out.print();
            //只能外部类使用
        }

        {
            MethodOut out = new MethodOut();
            out.print(1);
        }
    }
}
