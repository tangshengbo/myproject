package com.tangshengbo.design.oop.lsp;

/**
 * Created by Tangshengbo on 2017/10/20.
 */
public class Solder {

    private AbstractGun gun;

    public Solder(AbstractGun gun) {
        this.gun = gun;
    }

    public void killEnemy() {
        gun.shoot();
    }
}
