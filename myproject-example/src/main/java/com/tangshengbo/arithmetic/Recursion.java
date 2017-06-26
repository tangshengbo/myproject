package com.tangshengbo.arithmetic;

/**
 * Created by TangShengBo on 2017-05-19.
 */
public class Recursion {

    public int fact(int n) {
        if (n == 1) {
            return 1;
        }
        System.out.println(n);
        return fact(n - 1);
    }

    public static void main(String[] args) {
        Recursion recursion = new Recursion();
        System.out.println(recursion.fact(10));
    }

}