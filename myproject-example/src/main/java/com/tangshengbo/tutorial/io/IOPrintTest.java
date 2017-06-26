package com.tangshengbo.tutorial.io;

import java.io.*;

/**
 * Created by TangShengBo on 2017-06-25.
 */
public class IOPrintTest {

    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String line;

        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream("E:\\log.txt", true), true);
            PrintStream ps = new PrintStream(new FileOutputStream("E:\\log4j.txt", true), true);
            //System.setOut(ps);
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                pw.println(line);
                ps.println(line);
            }
            pw.close();
            br.close();
            isr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
