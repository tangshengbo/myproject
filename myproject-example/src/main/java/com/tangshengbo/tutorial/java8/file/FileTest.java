package com.tangshengbo.tutorial.java8.file;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by Tangshengbo on 2017/11/28.
 */
public class FileTest {

    public static void main(String[] args) {
        filterFile();
    }

    private static void filterFile() {
        File[] hiddenFilesA = new File("E:/").listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.getName().equals("cas.xml");
            }
        });
        System.out.println(hiddenFilesA.length);

        File[] hiddenFilesB = new File(".").listFiles(File::isHidden);
        System.out.println(hiddenFilesB.length);
    }
}
