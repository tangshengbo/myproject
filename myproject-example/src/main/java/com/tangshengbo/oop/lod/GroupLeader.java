package com.tangshengbo.oop.lod;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by TangShengBo on 2017-10-22.
 */
public class GroupLeader {

    private List<Girl> girls = Lists.newArrayList();

    public GroupLeader(List<Girl> girls) {
        this.girls = girls;
    }

    public int count() {
        return girls.size();
    }
}
