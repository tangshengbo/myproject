package com.tangshengbo.design.build;

/**
 * Created by Tangshengbo on 2017/10/12.
 */
public class BuilderTest {

    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder();
        Director director = new Director();
        director.setBuilder(builder);
        Product product = director.construct();
        product.show();
    }
}
