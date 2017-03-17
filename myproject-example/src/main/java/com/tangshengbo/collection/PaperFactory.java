package com.tangshengbo.collection;

/**
 * Created by Administrator on 2016/11/30.
 */
public class PaperFactory {

    private Paper paperBese = new Paper();

    public Paper createPaper() throws CloneNotSupportedException {
        return (Paper) paperBese.clone();
    }
}
