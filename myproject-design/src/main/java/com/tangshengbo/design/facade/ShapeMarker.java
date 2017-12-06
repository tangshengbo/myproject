package com.tangshengbo.design.facade;

/**
 * Created by tangshengbo on 2017/3/7.
 */
public class ShapeMarker {

    private Shape rectangle;

    private Shape circle;

    private Shape square;

    public ShapeMarker() {
        rectangle = new Rectangle();
        circle = new Circle();
        square = new Square();
    }

    public void drawRectangle() {
        rectangle.draw();
    }

    public void drawCircle() {
        circle.draw();
    }

    public void drawSquare() {
        square.draw();
    }
}
