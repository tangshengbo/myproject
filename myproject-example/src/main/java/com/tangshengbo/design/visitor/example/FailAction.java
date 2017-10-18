package com.tangshengbo.design.visitor.example;

/**
 * Created by Tangshengbo on 2017/10/18.
 */
public class FailAction extends AbstractAction implements Action {

    public FailAction(String name) {
        super(name);
    }

    @Override
    public String getManConclusion(Man man) {
        return String.format("%s %s 时,闷头喝酒,谁也劝不动", man.getName(), getName());
    }

    @Override
    public String getWomanConclusion(Woman woman) {
        return String.format("%s %s 时,眼泪汪汪,谁也劝不了", woman.getName(), getName());
    }
}
