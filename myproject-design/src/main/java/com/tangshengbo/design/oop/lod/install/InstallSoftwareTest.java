package com.tangshengbo.design.oop.lod.install;

/**
 * Created by TangShengBo on 2017-10-22.
 */
public class InstallSoftwareTest {

    public static void main(String[] args) {
        Wizard wizard = new Wizard();
        InstallSoftware installSoftware = new InstallSoftware();
        installSoftware.installWizard(wizard);
    }
}
