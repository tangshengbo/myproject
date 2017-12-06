package com.tangshengbo.design.oop.lod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TangShengBo on 2017-10-22.
 */
public class GirlTest {

    public static void main(String[] args) {
        List<Girl> girls = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            girls.add(new Girl());
        }

        GroupLeader groupLeader = new GroupLeader(girls);

        Teacher teacher = new Teacher();
        teacher.command(groupLeader);
    }
}
