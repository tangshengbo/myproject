package com.tangshengbo.template.velocity;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tangshengbo on 2018/12/30
 */
public class VelocityTest {

    @Test
    public void testSimpleVelocity() throws Exception {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.setProperty(RuntimeConstants.INPUT_ENCODING, "UTF-8");
        ve.setProperty(RuntimeConstants.OUTPUT_ENCODING, "UTF-8");
        ve.init();
//
//        List<String> classNameList = FileUtils.readLines(FileUtils.getFile("E:/code/input.txt"), "UTF-8");
//
//        for (String classNameUpCase : classNameList) {
////            Template t = ve.getTemplate("EstateMapper.vm");
//            String[] split = classNameUpCase.split(",");
//            classNameUpCase = split[0];
//            String classNameUpCaseSrc = split[1];
//            String classNameComment = split[2];
//            String classNameUpdate = split[3];
//            Template t = ve.getTemplate("job.vm");
//            VelocityContext ctx = new VelocityContext();
//            ctx.put("classNameUpCase", classNameUpCase);
//            ctx.put("classNameUpCaseName", lowerFirstCapse(classNameUpCase));
//            ctx.put("classNameUpCaseSrc", classNameUpCaseSrc);
//            ctx.put("classNameUpCaseSrcName", lowerFirstCapse(classNameUpCaseSrc));
//            ctx.put("classNameComment", classNameComment);
//            ctx.put("classNameUpdate", classNameUpdate);
//            ctx.put("classNameUpdateLow", StringUtils.lowerCase(classNameUpdate));
//
//            StringWriter sw = new StringWriter();
//            t.merge(ctx, sw);
//            FileUtils.writeStringToFile(FileUtils.getFile(String.format("%s/%sJobHandler.java", "E:/code/job", classNameUpCase)),
//                    sw.toString(), Charset.forName("UTF-8"));
//            System.out.println(sw.toString());
//        }



//
//        List<String> classNameList = FileUtils.readLines(FileUtils.getFile("E:/code/input1.txt"), "UTF-8");
//
//        for (String classNameUpCase : classNameList) {
////            Template t = ve.getTemplate("EstateMapper.vm");
//            String[] split = classNameUpCase.split(",");
//            classNameUpCase = split[0];
//            String classNameComment = split[1];
//            Template t = ve.getTemplate("radardao.vm");
//            VelocityContext ctx = new VelocityContext();
//            ctx.put("classNameUpCase", classNameUpCase);
//            ctx.put("classNameUpCaseName", lowerFirstCapse(classNameUpCase));
//            ctx.put("classNameComment", classNameComment);
//            StringWriter sw = new StringWriter();
//            t.merge(ctx, sw);
//            FileUtils.writeStringToFile(FileUtils.getFile(String.format("%s/%sDAO.java", "E:/code/dao", classNameUpCase)),
//                    sw.toString(), StandardCharsets.UTF_8);
//            System.out.println(sw.toString());
//        }

        BigDecimal bigDecimal = BigDecimal.valueOf(0.000001);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

//        System.out.println(new Date(timestamp.getTime()));
//        Long time = null;
//        Date date = new Date(time);
//        Instant instant = Instant.ofEpochMilli(date.getTime());
//
//        LocalDate beforeDate = LocalDate.now().minusDays(7);
//        System.out.println(beforeDate);
//
//
//        LocalDate issueDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
//        System.out.println(issueDate.isBefore(beforeDate));

        List<String> tradeDeviationDOList = new ArrayList<>();
        List<String> xxxx = Collections.emptyList();
        tradeDeviationDOList.addAll(xxxx);



//        System.out.println(bigDecimal.compareTo(BigDecimal.ZERO));

    }


    public static String lowerFirstCapse(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}