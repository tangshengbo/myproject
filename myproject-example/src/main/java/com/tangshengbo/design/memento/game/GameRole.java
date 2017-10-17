package com.tangshengbo.design.memento.game;

/**
 * Created by TangShengBo on 2017-10-16.
 */
public class GameRole {

    private int vit ;

    private int atk;

    private int def;

    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public RoleStateMemento saveState() {
        return new RoleStateMemento(vit, atk, def, score);
    }

    public void recoveryState(RoleStateMemento roleStateMemento) {
        this.vit = roleStateMemento.getVit();
        this.atk = roleStateMemento.getAtk();
        this.def = roleStateMemento.getDef();
        this.score = roleStateMemento.getScore();
    }

    public void getInitState() {
        this.vit = 100;
        this.atk = 100;
        this.def = 100;
        this.score = 100;
    }

    public void fighting() {
        this.vit = 0;
        this.atk = 0;
        this.def = 0;
        this.score = 0;
    }

    public void displayState() {
        System.out.println("角色当前状态:");
        System.out.println(String.format("体力: %s", this.vit));
        System.out.println(String.format("攻击力 : %s", this.atk));
        System.out.println(String.format("防御力 : %s", this.def));
        System.out.println(String.format("得分 : %s", this.score));
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
