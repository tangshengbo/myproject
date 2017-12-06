package com.tangshengbo.design.memento.game;


/**
 * Created by TangShengBo on 2017-10-16.
 */
public class GameRoleTest {

    public static void main(String[] args) {
        System.out.println("==============开始======================");
        GameRole gameRole = new GameRole();
        gameRole.getInitState();
        gameRole.displayState();
        RoleStateCaretaker roleStateCaretaker = new RoleStateCaretaker();
        roleStateCaretaker.setRoleStateMemento(gameRole.saveState());

        System.out.println("===============战斗=====================");
        gameRole.fighting();
        gameRole.displayState();

        System.out.println("===============恢复====================");
        gameRole.recoveryState(roleStateCaretaker.getRoleStateMemento());
        gameRole.displayState();
    }
}
