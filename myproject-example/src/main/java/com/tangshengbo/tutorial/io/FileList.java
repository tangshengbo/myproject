package com.tangshengbo.tutorial.io;

import java.io.File;

/**
 * Created by TangShengBo on 2017-07-23.
 */
public class FileList {

    public static void main(String[] args) {
        File file = new File("G:/resource");
        System.out.println(file.getName());
        tree(file, 0);
    }

    private static void tree(File file, int level) {
        System.out.println("level:\t" + level);
        String preStr = "";
        for (int i = 0; i <= level; i++) {
            preStr += "\t";
        }

        //获取该目录下所有子文件
        File[] files = file.listFiles();

        for (int i = 0; i < files.length; i++) {
            System.out.println(preStr + files[i].getName());
            if (files[i].isDirectory()) {
                System.out.println("retry level:\t" + level);
                tree(files[i], level + 1);
            }
        }
    }
}
