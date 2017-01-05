package com.tangshengbo.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/12/29.
 */
@Service("taskJob")
public class TaskJob {

    private final Log log = LogFactory.getLog(TaskJob.class);

    public void doJob() {

        log.info("TaskJob>>>>>>>>>>>>start>>>>>>>>>");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.info("QuartzJob Exception" + e.getStackTrace());
        }

        log.info("TaskJob>>>>>>>>>>>>end>>>>>>>>>");

    }


}
