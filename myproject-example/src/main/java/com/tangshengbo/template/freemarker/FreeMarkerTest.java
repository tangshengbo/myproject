package com.tangshengbo.template.freemarker;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ReUtil;
import com.google.common.collect.HashMultimap;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Tangshengbo on 2018/12/30
 */
public class FreeMarkerTest {

    @Test
    public void testSimpleFreemarker() throws Exception {
//        Configuration config = new Configuration(Configuration.VERSION_2_3_28);
//        config.setDefaultEncoding("UTF-8");
//        config.setNumberFormat("#.##");
//        config.setEncoding(Locale.CHINA,"UTF-8");
//        config.setTemplateLoader(new ClassTemplateLoader(FreeMarkerTest.class.getClassLoader(), ""));
//        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
//        config.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
//        config.setDateFormat("yyyy-MM-dd");
//        config.setTimeFormat("HH:mm:ss");
//        config.setCacheStorage(new MruCacheStorage(20, 200));
//        Template template = config.getTemplate("freemarker.ftl");
//        Map<String, Object> param = new HashMap<>();
//        param.put("name",  String.format("尊敬的【%s】 您的验证码为:%d", "唐唐", 1234));
//        param.put("date", new Date());
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            list.add(i);
//        }
//        param.put("numberList", list);
//        StringWriter sw = new StringWriter(1024);
//        template.process(param, sw);
//        System.out.println(sw);

        String str = "234234#唐剩下#32, (32432432)";
        System.out.println(getId(str));
        File file = new File("sample.txt");
//        万达购物中心(室内)
        int[] arr = {1, 2, 3};
//         Arrays
        System.out.println();
//万达购物中心(室内)(洒洒水) (手动挡)
//
        String Node = "";
        String s = null;
        System.out.println(s = Node.replaceAll("（", "("));
        System.out.println(s = s.replaceAll("）", ")"));

        Node = "万达购物中心（室内）（洒洒水） （手动挡）";

        s = Node.replaceAll("（\\*）", "()");//替换中文括号
        System.out.println(s);


        List<String> list = Arrays.asList("（室内））））））", "（洒洒水）", "（手动挡）");

//(室内), (洒洒水), (手动挡)
        System.out.println(convertEnglishBracket(list));

//        Timestamp entryTime = Timestamp.valueOf(LocalDateTime.now());
//        if (Objects.nonNull(entryTime)) {
//            LocalDate localDate = entryTime.toLocalDateTime().toLocalDate();
//            long timestamp = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
//
//            System.out.println(Timestamp.from(Instant.ofEpochMilli(timestamp)));
//            System.out.println(entryTime.toLocalDateTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
//        }


        String date = "2020-10";
        System.out.println(DateUtil.parse(date, "yyyy-MM").getTime());

//        Date.valueOf(LocalDate.parse(date));

        HashMultimap<String, String> multimap = HashMultimap.create();
        List<String> list1 = Arrays.asList("xx", "ss", "de");

        multimap.putAll("tang", list1);
//        System.out.println(SecureUtil.md5());
        System.out.println(multimap.get("tang"));


    }

    private static List<String> convertEnglishBracket(List<String> comChiNameList) {
        return comChiNameList.stream().map(str -> {
            if (StringUtils.isNotBlank(str)) {
                str = ReUtil.replaceAll(str, "（", "(");
                return ReUtil.replaceAll(str, "）", ")");
            }
            return str;
        }).collect(Collectors.toList());
    }

    private static String getId(String str){
        Pattern pat = Pattern.compile("#(.*?)#");
        Matcher mat = pat.matcher(str);
        if (mat.find()) {
            return mat.group(1);
        }
        return "";
    }
    
}
