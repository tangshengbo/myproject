package com.tangshengbo.design.memento.game;

import java.io.Serializable;

/**
 * Created by TangShengBo on 2017-10-16.
 */
public class RoleStateMemento implements Serializable {

    private int vit ;

    private int atk;

    private int def;

    private int score;

    public RoleStateMemento(int vit, int atk, int def) {
        this.vit = vit;
        this.atk = atk;
        this.def = def;
    }

    public RoleStateMemento(int vit, int atk, int def, int score) {
        this.vit = vit;
        this.atk = atk;
        this.def = def;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getVit() {
        return vit;
    }

    public void setVit(int vit) {
        this.vit = vit;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }
}
