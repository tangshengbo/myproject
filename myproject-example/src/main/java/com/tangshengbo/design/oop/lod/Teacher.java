package com.tangshengbo.design.oop.lod;

/**
 * Created by TangShengBo on 2017-10-22.
 */
public class Teacher {

    public void command(GroupLeader groupLeader) {
        System.out.println(String.format("全部女生数量 %s", groupLeader.count()));
    }

}
