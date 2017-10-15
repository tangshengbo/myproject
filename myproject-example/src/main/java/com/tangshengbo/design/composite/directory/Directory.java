package com.tangshengbo.design.composite.directory;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by TangShengBo on 2017-10-15.
 */
public class Directory extends Entry {

    private String name;

    private List<Entry> entries = Lists.newArrayList();

    public Directory(String name) {
        this.name = name;
    }

    @Override
    public Entry add(Entry entry) {
        entries.add(entry);
        entry.parent = this;
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        int size = 0;
//        Iterator<Entry> iterator = entries.iterator();
//        while (iterator.hasNext()) {
//            Entry entry = iterator.next();
//            size += entry.getSize();
//        }
        for (Entry entry : entries) {
            size += entry.getSize();
        }
        return size;
    }

    @Override
    protected void printList(String prefix) {
        System.out.println(prefix + "/" + this);
        for (Entry entry : entries) {
            entry.printList(prefix + "/" + name);
        }
    }
}
