package com.tangshengbo.arithmetic;

import com.tangshengbo.design.oop.ocp.Book;
import com.tangshengbo.design.oop.ocp.ComputerBookImpl;

import java.util.Objects;

/**
 * Created by Tangshengbo on 2017/10/24.
 */
public class ObjectsTest {

    public static void main(String[] args) {
        ObjectsTest test = new ObjectsTest();
//        test.compareHashCode();
//        test.isNull();
        test.compareObject();
    }

    public void compareArray() {
        String[] array = new String[]{"1", "2"};
        String[] array2 = new String[]{"1", "2"};
        System.out.println(Objects.deepEquals(array, array2));
    }

    public void compareObject() {
        String orderNo = "45454646542343246";
        String orderNo2 = "45454646542343246";
        Computer computer = new Computer(12, "戴尔");
        Computer computer1 = new Computer(12, "戴尔");
        System.out.println(computer + "\t" + computer1);
        System.out.println(Objects.equals(orderNo, orderNo2));
        System.out.println(Objects.equals(computer, computer1));
    }

    public void compareHashCode() {
        String orderNo = "45454646542343246";
        String orderNo2 = "45454646542343246";
        int result = orderNo.hashCode();
        result = 31 * 17 + result;
        System.out.println(result + "\t" + Objects.hash(orderNo));
    }

    public void isNull() {
        System.out.println(Objects.isNull(null));
        System.out.println(Objects.nonNull(null));
        String string;
        System.out.println(Objects.nonNull(string = ""));
    }

    public void objectToString() {
        Objects.requireNonNull(null, "对象不能为空");
        System.out.println(Objects.toString(null));
        Book book = new ComputerBookImpl("JavaWeb核心技术", 6700, "许令波", "编程技术");
        book = null;
        System.out.println(book.toString());
        System.out.println(Objects.toString(book, "该对象为空"));
    }

    public void hash() {
        String string = "123456";
//        string = null;
        System.out.println(Objects.hashCode(string));
        System.out.println(Objects.hash(string));
    }
}
