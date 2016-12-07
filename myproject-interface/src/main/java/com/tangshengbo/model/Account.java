package com.tangshengbo.model;

import java.io.Serializable;

public class Account implements Serializable {
    private Integer id;

    private String name;

    private Double money;

    public final Integer getId() {
        return id;
    }

    public final void setId(Integer id) {
        this.id = id;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public final Double getMoney() {
        return money;
    }


    public final void setMoney(Double money) {
        this.money = money;
    }


    @Override
    public String toString() {
        return "Account [id=" + id + ", name=" + name + ", money=" + money + "]";
    }

}