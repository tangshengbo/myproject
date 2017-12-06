package com.tangshengbo.design.visitor;

/**
 * Created by Tangshengbo on 2017/10/18.
 */
public class Man extends Person {

    public Man(String name, String action) {
        super(name, action);
    }

    @Override
    public String getConclusion() {
        if ("成功".equals(action)) {
            return String.format("%s %s 时,背后多半有一个女人", name, action);
        }
        if ("失败".equals(action)) {
            return String.format("%s %s 时,闷头喝酒,谁也劝不动", name, action);
        }
        if ("恋爱".equals(action)) {
            return String.format("%s %s 时,凡事不懂也要装懂", name, action);
        }
        return null;
    }
}
