package com.tangshengbo.tutorial.txt.util;

import com.tangshengbo.tutorial.txt.entity.ImportParams;
import com.tangshengbo.tutorial.txt.service.TxtImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;



/**
 * Created by Tangshengbo on 2018/2/2.
 */
public class TxtImportUtil {

    private static final Logger logger = LoggerFactory.getLogger(TxtImportUtil.class);

    public static <T> List<T> importExcel(File file, Class<?> pojoClass, ImportParams params) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            return new TxtImportService().importTxtToList(in, pojoClass, params);
        } catch (Exception e) {
            logger.error("{}", e);
        }
        return null;
    }

}
