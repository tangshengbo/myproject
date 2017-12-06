package com.tangshengbo.design.visitor;

/**
 * Created by Tangshengbo on 2017/10/18.
 */
public class Woman extends Person {

    public Woman(String name, String action) {
        super(name, action);
    }

    @Override
    public String getConclusion() {
        if ("成功".equals(action)) {
            return String.format("%s %s 时,背后多半有一个不成功的男人", name, action);
        }
        if ("失败".equals(action)) {
            return String.format("%s %s 时,眼泪汪汪,谁也劝不了", name, action);
        }
        if ("恋爱".equals(action)) {
            return String.format("%s %s 时,遇事懂也装不懂", name, action);
        }
        return null;
    }
}
