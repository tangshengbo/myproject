package com.tangshengbo.design.facade;

/**
 * Created by tangshengbo on 2017/3/7.
 */
public class FacedTest {

    public static void main(String[] args) {
        ShapeMarker marker = new ShapeMarker();
        marker.drawCircle();
        marker.drawRectangle();
        marker.drawSquare();
    }
}
