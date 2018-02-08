package com.tangshengbo.thread.forkjoin;

import jodd.util.ThreadUtil;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.io.*;
import java.util.zip.GZIPOutputStream;

public class GZipRunnable implements Runnable {

    private final File input;

    public GZipRunnable(File input) {
        this.input = input;
    }

    @Override
    public void run() {
        ThreadUtil.sleep(1000);
        // don't compress an already compressed file
        if (input.getName().endsWith(".gz")) {
            return;
        }
        File output = new File(input.getParent(), input.getName() + ".gz");
        if (output.exists()) {
            return;
        }
        // Don't overwrite an existing file
        try (InputStream in = new BufferedInputStream(new FileInputStream(input));
             OutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(output)))) {
            int b;
            while ((b = in.read()) != -1) out.write(b);
            out.flush();
        } catch (IOException ex) {
            System.out.println(ExceptionUtils.getStackTrace(ex));
        }
    }
}