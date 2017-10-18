package com.tangshengbo.design.visitor.example;

/**
 * Created by Tangshengbo on 2017/10/18.
 */
public class SuccessAction extends AbstractAction implements Action {

    public SuccessAction(String name) {
        super(name);
    }

    @Override
    public String getManConclusion(Man man) {
        return String.format("%s %s 时,背后多半有一个女人", man.getName(), getName());
    }

    @Override
    public String getWomanConclusion(Woman woman) {
        return String.format("%s %s 时,背后多半有一个不成功的男人", woman.getName(), getName());
    }
}
