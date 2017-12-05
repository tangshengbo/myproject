package com.tangshengbo.exception;

import jodd.exception.ExceptionUtil;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TangShengBo on 2017-11-18.
 */
public class HideException {

    public void readTwoFile() throws CustomException {
        BufferedReader br1 = null;
        BufferedReader br2 = null;
        FileReader fr;
        List<Throwable> list = new ArrayList<>();
        try {
            fr = new FileReader("A.txt"); // 1
            br1 = new BufferedReader(fr);
            // process code1....
            fr = new FileReader("B.txt"); // 3
            br2 = new BufferedReader(fr);
            // process code2
        } catch (IOException ie) {
            list.add(ie);//防止丢弃异常
        } finally {
            if (br1 != null) {
                try {
                    br1.close();
                } catch (IOException ie) {
                    list.add(ie);//防止丢弃异常
                }
            }
            if (br2 != null) {
                try {
                    br2.close();
                } catch (IOException ie) {
                    list.add(ie);//防止丢弃异常
                }
            }
        }
        // 检查异常的数目

        if (list.size() > 0) {
            ExceptionUtil.throwException(new CustomException(list));
        }
    }

    public static void main(String[] args) {
        HideException hideException = new HideException();
        try {
            hideException.readTwoFile();
        } catch (CustomException e) {
            System.out.println(ExceptionUtils.getStackTrace(e));
            System.out.println(e.getExceptions());
        }
    }
}
