package com.tangshengbo.io.util;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by Tangshengbo on 2017/11/25.
 */
public class IOUtilsTest {

    public static void main(String[] args) {
        downloadFileToList();
//        uploadFileByList();
    }

    private static void downloadFileToList() {
        try {
            List<String> lines = IOUtils.readLines(new FileInputStream("E:/20171114_3000000255_ALLBALANCE.txt"), StandardCharsets.UTF_8);
            lines.stream().skip(1).forEach(System.out::println);
            System.out.println("--" + lines.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void uploadFileByList() {
        OutputStream os = null;
        try{
            os = new FileOutputStream("E:/20171114_3000000255_ALLBALANCE.txt");
            List<String> lines = IOUtils.readLines(new FileInputStream("D:/20171114_3000000255_ALLBALANCE.txt"), StandardCharsets.UTF_8);
            IOUtils.writeLines(lines, "\n", os);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(os);
        }
    }
}
