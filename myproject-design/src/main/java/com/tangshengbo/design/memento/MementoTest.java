package com.tangshengbo.design.memento;

/**
 * Created by TangShengBo on 2017-10-16.
 */
public class MementoTest {

    public static void main(String[] args) {
        Originator originator = new Originator();
        originator.setState("On");
        originator.show();

        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(originator.createMemento());

        originator.setState("Off");
        originator.show();

        originator.setState(caretaker.getMemento().getState());
        originator.show();
    }
}
