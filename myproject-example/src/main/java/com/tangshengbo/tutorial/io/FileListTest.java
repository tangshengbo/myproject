package com.tangshengbo.tutorial.io;


import com.google.common.collect.Lists;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * Created by TangShengBo on 2017-07-23.
 */
public class FileListTest {

    private List<File> fileList = Lists.newArrayListWithExpectedSize(1000000);

    public static void main(String[] args) {
        File file = new File("D:/");
        FileListTest fileListTest = new FileListTest();
        System.out.println(file.getName());
        fileListTest.tree(file, 0);
        long ts = System.currentTimeMillis();
        Optional<File> maxFile;
        maxFile = getMaxFileB(fileListTest);
        OptionalDouble maxSize = getAvgFileSize(fileListTest);
        long te = System.currentTimeMillis();
        print(fileListTest, ts, maxFile, maxSize, te);
    }

    private static void print(FileListTest fileListTest, long ts, Optional<File> maxFile, OptionalDouble maxSize, long te) {
        System.out.println("===================================================");
        System.out.println(String.format("+ cost %s ms", (te - ts) / 1000.0));
        System.out.println("average:" + formatFileSize(new Double(maxSize.orElse(0.0)).longValue()));
        System.out.println(maxFile + "\tsize:"
                + formatFileSize(maxFile.isPresent()? maxFile.get().length():0L)
                + "\t total:" + fileListTest.fileList.size() + "个");
    }

    private static OptionalDouble getAvgFileSize(FileListTest fileListTest) {
        return fileListTest.fileList.parallelStream()
                    .mapToLong(File::length)
                    .average();
    }

    private static Optional<File> getMaxFileB(FileListTest fileListTest) {
        Optional<File> maxFile;Comparator<File> fileComparator = Comparator.comparingLong(File::length);
        maxFile = fileListTest.fileList
                .parallelStream()
                .collect(Collectors.maxBy(fileComparator));
        return maxFile;
    }

    private static Optional<File> getMaxFileA(FileListTest fileListTest) {
        return fileListTest
                .fileList.parallelStream()
                .max(comparing(File::length));
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
            for (File f : files) {
                String fileSize = formatFileSize(f.length());
                System.out.println(preStr + f.getName() + "\t size:" + fileSize);
                if (f.isDirectory()) {
                    System.out.println("retry level:\t" + level);
                    tree(f, level + 1);
                } else {
                    fileList.add(f);
                }
            }
        }
    }

    //转换文件大小
    private static String formatFileSize(long fileSize) {
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
