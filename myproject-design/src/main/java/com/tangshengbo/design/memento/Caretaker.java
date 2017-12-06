package com.tangshengbo.design.memento;

/**
 * Created by TangShengBo on 2017-10-16.
 */
public class Caretaker {

    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
