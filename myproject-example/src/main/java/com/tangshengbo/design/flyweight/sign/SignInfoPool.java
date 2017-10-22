package com.tangshengbo.design.flyweight.sign;

/**
 * Created by TangShengBo on 2017-10-22.
 */
public class SignInfoPool extends SignInfo {

    //定义一个对象池提取的KEY值
    private String key;

    //SignInfoPool
    public SignInfoPool(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
