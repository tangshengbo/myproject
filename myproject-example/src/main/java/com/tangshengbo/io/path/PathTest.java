package com.tangshengbo.io.path;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Created by Tangshengbo on 2017/11/19.
 */
public class PathTest {

    public static void main(String[] args) {
        readFile();
        writeFile();
    }

    private static void readFile() {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get("E:/", "tang.txt"));
            String content = new String(bytes, StandardCharsets.UTF_8);
            System.out.println("readAllBytes:" + content);
            List<String> stringList = Files.readAllLines(Paths.get("E:/", "tang.txt"));
            stringList.forEach(s -> {
                System.out.println(s);
            });
            System.out.println("======================================================================================");
            Files.lines(Paths.get("E:/", "tang.txt")).skip(1).forEach(s -> {
                System.out.println(s);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFile() {
        try {
            Files.write(Paths.get("E:/files.txt"), "Hello JDK7!唐唐".getBytes(), StandardOpenOption.APPEND);
            System.out.println("OK");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
