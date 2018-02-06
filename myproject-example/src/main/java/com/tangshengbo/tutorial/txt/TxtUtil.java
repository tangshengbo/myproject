package com.tangshengbo.tutorial.txt;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Tangshengbo on 2018/2/5.
 */
@SuppressWarnings("unchecked")
public class TxtUtil {

    public static  <T> List<T> importTxt(InputStream in, Class<T> entityClass, ImportParams params) {
        return (List<T>) new TxtImport<>().readFromStream(in, (Class<Object>) entityClass, params);
    }

    public static  <T> List<T> importTxt(List<String> lines, Class<?> entityClass, ImportParams params) {
        return (List<T>) new TxtImport<>().readFromString(lines, (Class<Object>) entityClass, params);
    }
}
