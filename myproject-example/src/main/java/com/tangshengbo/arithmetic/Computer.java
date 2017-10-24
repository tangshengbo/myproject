package com.tangshengbo.arithmetic;

import java.util.Objects;

/**
 * Created by Tangshengbo on 2017/10/24.
 */
public class Computer {

    private int model;

    private String brand;

    public Computer(int model, String brand) {
        this.model = model;
        this.brand = brand;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Computer computer = (Computer) o;
        return model == computer.model && Objects.equals(brand, computer.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, brand);
    }
}
