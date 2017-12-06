package com.tangshengbo.design.oop.lsp;

/**
 * Created by Tangshengbo on 2017/10/20.
 */
public class GunTest {

    public static void main(String[] args) {
        AbstractGun gun = new MachineGun();
        Solder solder = new Solder(gun);
        solder.killEnemy();
    }
}
