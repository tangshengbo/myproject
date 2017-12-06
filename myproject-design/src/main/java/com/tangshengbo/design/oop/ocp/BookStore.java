package com.tangshengbo.design.oop.ocp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TangShengBo on 2017-10-22.
 */
public class BookStore {

    private final static List<Book> BOOK_LIST = new ArrayList<>();

    static {
//        BOOK_LIST.add(new NovelBook("天龙八部", 3200, "金庸"));
//        BOOK_LIST.add(new NovelBook("巴黎圣母院", 5600, "雨果"));
//        BOOK_LIST.add(new NovelBook("悲惨世界", 3500, "雨果"));
//        BOOK_LIST.add(new NovelBook("金瓶梅", 4300, "兰陵笑笑生"));
        BOOK_LIST.add(new OffNoveBook("天龙八部", 3200, "金庸"));
        BOOK_LIST.add(new OffNoveBook("巴黎圣母院", 5600, "雨果"));
        BOOK_LIST.add(new OffNoveBook("悲惨世界", 3500, "雨果"));
        BOOK_LIST.add(new OffNoveBook("金瓶梅", 4300, "兰陵笑笑生"));
        BOOK_LIST.add(new ComputerBookImpl("设计模式", 5000, "唐唐", "软件工程"));
        BOOK_LIST.add(new ComputerBookImpl("JavaWeb核心技术", 6700, "许令波", "编程技术"));

    }

    public static void main(String[] args) {
        System.out.println("====================书店卖出去的书籍如下:============");
        for (Book book : BOOK_LIST) {
            System.out.println(book);
        }
    }
}
