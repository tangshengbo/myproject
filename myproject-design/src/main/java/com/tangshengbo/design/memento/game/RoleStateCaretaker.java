package com.tangshengbo.design.memento.game;

import java.io.*;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 * Created by TangShengBo on 2017-10-16.
 */
public class RoleStateCaretaker {

    public static final String SERVICE_FILE_NAME = "game.bat";

    private RoleStateMemento roleStateMemento;

    public RoleStateMemento getRoleStateMemento() {
        try (ObjectInput in = new ObjectInputStream(new InflaterInputStream(new FileInputStream(SERVICE_FILE_NAME)))) {
            roleStateMemento = (RoleStateMemento) in.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return roleStateMemento;
    }

    public void setRoleStateMemento(RoleStateMemento roleStateMemento) {
        this.roleStateMemento = roleStateMemento;
        try (ObjectOutput output = new ObjectOutputStream(new DeflaterOutputStream(new FileOutputStream(SERVICE_FILE_NAME)))) {
            output.writeObject(this.roleStateMemento);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
