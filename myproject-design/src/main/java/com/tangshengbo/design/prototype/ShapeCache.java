package com.tangshengbo.design.prototype;

import java.util.Hashtable;

/**
 * Created by tangshengbo on 2017/3/5.
 */
public class ShapeCache {

    private static Hashtable<String, Shape> shapes;

    static {
        shapes = new Hashtable<String, Shape>();
    }

    public static Shape getShape(String shapeId) throws CloneNotSupportedException {
        Shape shape = shapes.get(shapeId);
        return (Shape) shape.clone();
    }

    public static void loadShapes() {
        Circle circle = new Circle();
        circle.setId("1");
        shapes.put(circle.getId(), circle);

        Square square = new Square();
        square.setId("2");
        shapes.put(square.getId(), square);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("3");
        shapes.put(rectangle.getId(), rectangle);
    }
}
