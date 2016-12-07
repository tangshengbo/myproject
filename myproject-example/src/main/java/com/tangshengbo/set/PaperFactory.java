package com.tangshengbo.set;

/**
 * Created by Administrator on 2016/11/30.
 */
public class PaperFactory {

    public Paper paperBese = new Paper();

    public Paper createPaper() throws CloneNotSupportedException {
        return (Paper) paperBese.clone();
    }
}
