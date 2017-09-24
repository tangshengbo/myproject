package com.tangshengbo.design.decorate.example;

/**
 * Created by TangShengBo on 2017-09-24.
 */
public class Hat extends Costume {

    @Override
    public void show() {
        System.out.println("Hat..............");
        super.show();
    }
}
