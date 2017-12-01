package com.tangshengbo.tutorial.io;

import com.google.common.collect.Lists;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by TangShengBo on 2017-07-23.
 */
public class FileListTest {

    private List<File> fileList = Lists.newArrayListWithExpectedSize(100000);

    public static void main(String[] args) {
        File file = new File("E:/");
        FileListTest fileListTest = new FileListTest();
        System.out.println(file.getName());
        fileListTest.tree(file, 0);
        File maxFile = fileListTest.fileList.stream().max((f1, f2)
                -> Long.compare(f1.length(), f2.length())).get();
        System.out.println(maxFile + "\tsize:" + formatFileSize(maxFile.length())
                + "\t total:" + fileListTest.fileList.size() + "个");
    }

    private void tree(File file, int level) {
        System.out.println("level:\t" + level);
        String preStr = "";
        for (int i = 0; i <= level; i++) {
            preStr += "\t";
        }

        //获取该目录下所有子文件
        File[] files = file.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileSize = formatFileSize(files[i].length());
                System.out.println(preStr + files[i].getName() + "\t size:" + fileSize);
                if (files[i].isDirectory()) {
                    System.out.println("retry level:\t" + level);
                    tree(files[i], level + 1);
                } else {
                    fileList.add(files[i]);
                }
            }
        }
    }

    private static String formatFileSize(long fileSize) {//转换文件大小
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString;
        if (fileSize < 1024) {
            fileSizeString = df.format((double) fileSize) + "B";
        } else if (fileSize < 1048576) {
            fileSizeString = df.format((double) fileSize / 1024) + "K";
        } else if (fileSize < 1073741824) {
            fileSizeString = df.format((double) fileSize / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileSize / 1073741824) + "G";
        }
        return fileSizeString;
    }
}
