package com.tangshengbo.tutorial.txt;

import jodd.util.StringPool;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.util.List;

/**
 * Created by Tangshengbo on 2018/2/2.
 */
public class TxtImportTest {

    private static final Logger logger = LoggerFactory.getLogger(TxtImportTest.class);

    @Test
    public void testImport() throws Exception {
        ImportParams importParams = new ImportParams(2, "|", StringPool.UTF_8);
        List<BfTradeEntity> bfTradeEntityList = TxtUtil.importTxt(new FileInputStream("E:/BF_FI_1203634_20180204.txt"), BfTradeEntity.class, importParams);
        if (bfTradeEntityList != null) {
            bfTradeEntityList.stream().forEach(bfTradeEntity -> {
                logger.info("{}", bfTradeEntity);
            });
            logger.warn("{}", bfTradeEntityList.size());
        }
    }
}
