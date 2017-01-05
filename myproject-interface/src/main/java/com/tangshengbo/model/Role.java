package com.tangshengbo.model;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {

    private Long id;

    private String name;

    private String descn;

    private List<Authority> authoritys = Lists.newArrayList();

    public List<Authority> getAuthoritys() {
        return authoritys;
    }

    public void setAuthoritys(List<Authority> authoritys) {
        this.authoritys = authoritys;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescn() {
        return descn;
    }

    public void setDescn(String descn) {
        this.descn = descn == null ? null : descn.trim();
    }

    @Override
    public String toString() {
        return "Role{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", descn='" + descn + '\''
                + ", authoritys=" + authoritys
                + '}';
    }
}
