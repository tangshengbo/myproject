package com.tangshengbo.design.decorate.shape;

/**
 * Created by TangShengBo on 2017-09-24.
 */
public abstract class ShapeDecorator implements Shape {

    protected Shape shape;

    public ShapeDecorator(Shape shape) {
        this.shape = shape;
    }

    public void draw() {
        if (shape != null) {
            shape.draw();
        }
    }
}
