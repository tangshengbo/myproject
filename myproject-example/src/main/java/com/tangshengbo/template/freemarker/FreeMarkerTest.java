package com.tangshengbo.template.freemarker;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.MruCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.junit.Test;

import java.io.StringWriter;
import java.util.*;

/**
 * Created by Tangshengbo on 2018/12/30
 */
public class FreeMarkerTest {

    @Test
    public void testSimpleFreemarker() throws Exception {
        Configuration config = new Configuration(Configuration.VERSION_2_3_28);
        config.setDefaultEncoding("UTF-8");
        config.setNumberFormat("#.##");
        config.setEncoding(Locale.CHINA,"UTF-8");
        config.setTemplateLoader(new ClassTemplateLoader(FreeMarkerTest.class.getClassLoader(), ""));
        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        config.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
        config.setDateFormat("yyyy-MM-dd");
        config.setTimeFormat("HH:mm:ss");
        config.setCacheStorage(new MruCacheStorage(20, 200));
        Template template = config.getTemplate("freemarker.ftl");
        Map<String, Object> param = new HashMap<>();
        param.put("name",  String.format("尊敬的【%s】 您的验证码为:%d", "唐唐", 1234));
        param.put("date", new Date());
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        param.put("numberList", list);
        StringWriter sw = new StringWriter(1024);
        template.process(param, sw);
        System.out.println(sw);
    }
}
