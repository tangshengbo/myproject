package com.tangshengbo.arithmetic;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tangshengbo
 *
 * @author Tangshengbo
 * @date 2019/11/6
 */
public class PatternTest {

    public static void main(String[] args) {
        String managers = "教师10'0010'\n" +
                "教师11(0011)\n" +
                "教师9(009)\n" +
                "教师12(0012)\n" +
                "教师13(0013)\n" +
                "教师14(0014)";
        List<String> ls = new ArrayList<>();
        Pattern pattern = Pattern.compile("(?<=\\()(.+?)(?=\\))");
        Matcher matcher = pattern.matcher(managers);
        while (matcher.find()) {
            ls.add(matcher.group());

        }
        System.out.println(ls);
        String str = "select * from aa where a='xxx' and b='bbb' in('ccc','ddd') order by xx desc";
        Pattern p = Pattern.compile("(\'(.*?)\')");
        Matcher m = p.matcher(str);
        List<String> paramList = new ArrayList<>();
        int index = 0;
        while (m.find()) {
            String group = m.group();
            paramList.add(group);
            str = str.replace(group, "{" + index + "}");
            index++;
        }
        str = str.toUpperCase();
        String format = MessageFormat.format(str, paramList.toArray());
        System.out.println(format);
    }
}
