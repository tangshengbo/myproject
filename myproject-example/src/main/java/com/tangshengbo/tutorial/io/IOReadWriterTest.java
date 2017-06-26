package com.tangshengbo.tutorial.io;

import java.io.*;

/**
 * Created by TangShengBo on 2017-06-25.
 */
public class IOReadWriterTest {

    public static void main(String[] args) {
        copyFile("D:\\reader.txt", "E:\\writer.txt", "UTF-8");
        inputRead();
    }

    public static void copyFile(String srcFileName, String destFileName, String charsetName) {
        try {
         /* Reader reader = new FileReader("D:\\read.txt");
            Writer writer = new FileWriter("E:\\writer.txt");*/
            InputStreamReader isr = new InputStreamReader
                    (new FileInputStream(srcFileName), charsetName);
            OutputStreamWriter osw = new OutputStreamWriter
                    (new FileOutputStream(destFileName), charsetName);

            BufferedReader br = new BufferedReader(isr);
            BufferedWriter bw = new BufferedWriter(osw);

            String line;
            long startTime = System.currentTimeMillis(); // 获取开始时间
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                bw.newLine();
                bw.write(line);
            }
            //将缓冲区的数据全部写到目的地
            bw.flush();
            bw.close();
            br.close();

            long endTime = System.currentTimeMillis(); // 获取结束时间
            System.out.println("程序运行时间： " + (endTime - startTime) / 1000 + "s");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void inputRead() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s;

        try {
            s = br.readLine();
            while (s != null) {
                if (s.equalsIgnoreCase("exit")) {
                    break;
                }
                System.out.println(s.toUpperCase());
                s = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
