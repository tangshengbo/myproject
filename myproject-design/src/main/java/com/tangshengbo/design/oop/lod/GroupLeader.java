package com.tangshengbo.design.oop.lod;

import java.util.List;

/**
 * Created by TangShengBo on 2017-10-22.
 */
public class GroupLeader {

    private List<Girl> girls;

    public GroupLeader(List<Girl> girls) {
        this.girls = girls;
    }

    public int count() {
        return girls.size();
    }
}
