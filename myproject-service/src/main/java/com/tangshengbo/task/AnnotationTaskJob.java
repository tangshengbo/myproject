package com.tangshengbo.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/29.
 */
@Service("annotationTaskJob")
public class AnnotationTaskJob {

    private final Log log = LogFactory.getLog(AnnotationTaskJob.class);

    private Map<String,Object> resource = new HashMap<String,Object>();

    private final static String KEY = "tang";




    @Scheduled(cron = "*/2 * * * * ?")
    public void provider(){

        log.info("AnnotationTaskJob>>>>>>>>>>>>start>>>>>>>>>");

        try {
            resource.put(AnnotationTaskJob.KEY,"tangshengbo");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.info("QuartzJob Exception"+e.getStackTrace());
        }

        log.info("AnnotationTaskJob>>>>>>>>>>>>end>>>>>>>>>");

    }

    @Scheduled(cron = "*/2 * * * * ?")
    public void consumer(){

        log.info("AnnotationTaskJob>>>>>>>>>>>>start>>>>>>>>>");

        try {
        log.info("consumer{}:"+resource.get(AnnotationTaskJob.KEY));
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.info("QuartzJob Exception"+e.getStackTrace());
        }

        log.info("AnnotationTaskJob>>>>>>>>>>>>end>>>>>>>>>");

    }


}
