package com.tangshengbo.design.visitor.example;

/**
 * Created by Tangshengbo on 2017/10/18.
 */
public class MarriageAction extends AbstractAction implements Action {

    public MarriageAction(String name) {
        super(name);
    }

    @Override
    public String getManConclusion(Man man) {
        return String.format("%s %s 时,感叹道：恋爱游戏终结时 有妻徒刑 遥遥无期", man.getName(), getName());
    }

    @Override
    public String getWomanConclusion(Woman woman) {
        return String.format("%s %s 时,欣慰曰：爱情长跑路漫漫 婚姻保险保平安", woman.getName(), getName());
    }
}
