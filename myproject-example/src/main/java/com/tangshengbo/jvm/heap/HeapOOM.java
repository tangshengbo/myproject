package com.tangshengbo.jvm.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TangShengBo on 2017-05-16.
 */
public class HeapOOM {

    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();

        while (true) {
            list.add(new OOMObject());
        }
    }
}
