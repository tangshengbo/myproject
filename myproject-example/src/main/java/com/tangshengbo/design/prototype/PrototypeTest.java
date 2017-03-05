package com.tangshengbo.design.prototype;

/**
 * Created by tangshengbo on 2017/3/5.
 */
public class PrototypeTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        ShapeCache.loadShapes();

        Shape clonedShape = (Shape) ShapeCache.getShape("1");
        System.out.println("Shape : " + clonedShape.getType());

        Shape clonedShape2 = (Shape) ShapeCache.getShape("2");
        System.out.println("Shape : " + clonedShape2.getType());

        Shape clonedShape3 = (Shape) ShapeCache.getShape("3");
        System.out.println("Shape : " + clonedShape3.getType());

    }
}
