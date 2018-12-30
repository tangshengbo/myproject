package com.tangshengbo.template.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Test;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Tangshengbo on 2018/12/30
 */
public class VelocityTest {

    @Test
    public void testSimpleVelocity() {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();

        Template t = ve.getTemplate("velocity.vm");
        VelocityContext ctx = new VelocityContext();

        ctx.put("name", "velocity");
        ctx.put("date", (new Date()).toString());
        ctx.put("cn", "中国");
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            temp.add(String.valueOf(i));
        }
        ctx.put("list", temp);

        StringWriter sw = new StringWriter();
        t.merge(ctx, sw);

        System.out.println(sw.toString());
    }
}
