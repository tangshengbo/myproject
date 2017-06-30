package com.tangshengbo.jms.activemq;

import java.io.Serializable;

/**
 * Created by Tang on 2017/6/30.
 */
public class MessageObject implements Serializable{

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MessageObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
