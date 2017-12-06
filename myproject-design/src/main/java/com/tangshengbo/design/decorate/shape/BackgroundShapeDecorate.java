package com.tangshengbo.design.decorate.shape;

/**
 * Created by TangShengBo on 2017-09-24.
 */
public class BackgroundShapeDecorate extends ShapeDecorator {

    public BackgroundShapeDecorate(Shape shape) {
        super(shape);
    }

    @Override
    public void draw() {
        super.draw();
        setBackground(shape);
    }

    private void setBackground(Shape shape) {
        System.out.println("background:red");
    }
}
