package com.tangshengbo.jvm.methodarea;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TangShengBo on 2017-05-16.
 */
public class MethodAreaOOM {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
