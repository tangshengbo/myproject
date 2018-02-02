package com.tangshengbo.tutorial.txt;

import com.tangshengbo.tutorial.txt.entity.BfTradeEntity;
import com.tangshengbo.tutorial.txt.entity.ImportParams;
import com.tangshengbo.tutorial.txt.util.TxtImportUtil;
import jodd.util.StringPool;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

/**
 * Created by Tangshengbo on 2018/2/2.
 */
public class TxtImportTest {

    private static final Logger logger = LoggerFactory.getLogger(TxtImportTest.class);

    @Test
    public void testImport() {
        ImportParams importParams = new ImportParams(2, "|", StringPool.UTF_8);

        List<BfTradeEntity> bfTradeEntityList = TxtImportUtil.importExcel(new File("E:/BF_FI_1203634_20180129.txt"), BfTradeEntity.class, importParams);
        if (bfTradeEntityList != null) {
            bfTradeEntityList.forEach(bfTradeEntity -> {
                logger.info("{}", bfTradeEntity);
            });
        }
    }
}
