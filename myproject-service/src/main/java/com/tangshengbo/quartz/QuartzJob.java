package com.tangshengbo.quartz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by Administrator on 2016/12/29.
 */
public class QuartzJob {

    private final Log log = LogFactory.getLog(QuartzJob.class);

    public void doJob() {

        log.info("QuartzJob>>>>>>>>>>>>start>>>>>>>>>");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.info("QuartzJob Exception" + e.getStackTrace());
        }

        log.info("QuartzJob>>>>>>>>>>>>end>>>>>>>>>");

    }
}
