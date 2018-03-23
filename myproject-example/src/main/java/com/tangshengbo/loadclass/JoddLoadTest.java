package com.tangshengbo.loadclass;

import jodd.io.FileUtil;
import jodd.io.ZipUtil;
import jodd.io.findfile.FindFile;
import jodd.util.Base32;
import jodd.util.Base64;
import jodd.util.StringPool;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by Tangshengbo on 2018/3/23.
 */
public class JoddLoadTest {

    private static final Logger logger = LoggerFactory.getLogger(JoddLoadTest.class);

    @Test
    public void testFindFile() {
        FindFile ff = FindFile.wildcard()
                .matchOnlyFileName()
                .include("*.txt")
                .recursive(true)
                .includeDirs(true)
                .includeFiles(true)
                .searchPath("E:/");
        ff.forEach(f -> logger.info("{}, {}", f.getAbsolutePath(), f.length()));
    }

    @Test
    public void testZip() throws Exception {
        File file = ZipUtil.gzip("E:/03.xlsx");
        byte[] data = IOUtils.toByteArray(FileUtils.openInputStream(file));
        logger.info("Base32:{}", Base32.encode(data));
        ZipUtil.ungzip(file);
        logger.info("Base64:{}", Base64.encodeToString(data));
        logger.info("md5:{}", FileUtil.md5(file));
        logger.info("sha1:{}", FileUtil.sha1(file));
        logger.info("sha256:{}", FileUtil.sha256(file));
        logger.info("sha384:{}", FileUtil.sha384(file));
        logger.info("sha512:{}", FileUtil.sha512(file));
        FileUtil.appendString("E:/pool.txt", "糖果", StringPool.UTF_8);
    }
}
