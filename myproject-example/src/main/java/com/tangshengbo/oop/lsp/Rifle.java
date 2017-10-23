package com.tangshengbo.oop.lsp;

/**
 * Created by Tangshengbo on 2017/10/20.
 */
public class Rifle extends AbstractGun {

    @Override
    public void shoot() {
        System.out.println("步枪射击............");
    }
}
