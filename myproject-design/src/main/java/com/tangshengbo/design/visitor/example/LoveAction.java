package com.tangshengbo.design.visitor.example;

/**
 * Created by Tangshengbo on 2017/10/18.
 */
public class LoveAction extends AbstractAction implements Action {

    public LoveAction(String name) {
        super(name);
    }

    @Override
    public String getManConclusion(Man man) {
        return String.format("%s %s 时,凡事不懂也要装懂", man.getName(), getName());
    }

    @Override
    public String getWomanConclusion(Woman woman) {
        return String.format("%s %s 时,遇事懂也装不懂", woman.getName(), getName());
    }
}
